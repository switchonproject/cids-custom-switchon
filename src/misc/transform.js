'use strict';

var createStation, createIdf, f, files, fs, gp, i, idf, parseGeom, parseGP, parseIdf, sql, sys, ewkt;

fs = require('fs');
sys = require('sys');

parseGeom = function (line) {
	var e, n, split, srid;
	
	split = line.split(/[\s]+/);
	
	e = split[4].substr(0, split[4].indexOf('m'));
	n = split[6].substr(0, split[6].indexOf('m'));
	
	if (split[2].indexOf('M28') !== -1) {
		srid = 31281;
	} else if (split[2].indexOf('M31') !== -1) {
		srid = 31282;
	} else if (split[2].indexOf('M34') !== -1) {
		srid = 31283;
	} else {
		throw "cannot find srid, token '" + split[2] + "'";
	}
	
	return 'SRID=' + srid + ';POINT(' + e + ' ' + n + ')';
};

parseGP = function (line) {
	return line.split(/[\s]+/)[1];
};

parseIdf = function (file, begin) {
	var data, i, j, split;
	
	data = {};
	data.data = {};
	
	for (i = begin; i < file.length; i += 4) {
		split = file[i].split(/[\s]+/);
		data.data[split[2 + split.length - 16]] = {
			"1": parseFloat(split[4 + split.length - 16]),
			"2": parseFloat(split[5 + split.length - 16]),
			"3": parseFloat(split[6 + split.length - 16]),
			"5": parseFloat(split[7 + split.length - 16]),
			"10": parseFloat(split[8 + split.length - 16]),
			"20": parseFloat(split[9 + split.length - 16]),
			"25": parseFloat(split[10 + split.length - 16]),
			"30": parseFloat(split[11 + split.length - 16]),
			"50": parseFloat(split[12 + split.length - 16]),
			"75": parseFloat(split[13 + split.length - 16]),
			"100": parseFloat(split[14 + split.length - 16])
		};
	}
	
	return data;
};

createStation = function (ewkt, name, sql) {
	sql.push("INSERT INTO geom (geo_field) VALUES (ST_SetSrid(ST_Transform(ST_GeomFromEWKT('" + ewkt + "'), 4326), -1));");
	sql.push("INSERT INTO monitorstation (name, geom, type) VALUES ('" + name + "', (SELECT max(id) FROM geom), 'RF:urn:ogc:def:property:OGC:prec');");
};

createIdf = function (name, data, sql) {
	sql.push("INSERT INTO idf_curve (name, uri, station) VALUES ('" + name + "', '" + JSON.stringify(data) + "', (SELECT max(id) FROM monitorstation));");
};

files = fs.readdirSync('files');

sql = [];
sql.push('BEGIN;\n');

for (i = 0; i < files.length; ++i) {
	sys.puts("processing '" + files[i] + "'");
	
	f = fs.readFileSync('files/' + files[i]).toString().split('\n');
		
	ewkt = parseGeom(f[1]);
	gp = parseGP(f[1]);
	createStation(ewkt, f[1].substr(0, f[1].length - 1), sql);
	
	idf = parseIdf(f, 5);
	createIdf('MaxModN@' + gp, idf, sql);
	
	idf = parseIdf(f, 6);
	createIdf('Bemessung@' + gp, idf, sql);
	
	idf = parseIdf(f, 7);
	createIdf('KOSTRA@' + gp, idf, sql);
	
	sql.push('');
}
	
sql.push('COMMIT;');

fs.writeFileSync('idf.sql', sql.join('\n'));
