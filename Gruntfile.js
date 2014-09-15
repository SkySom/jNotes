/**
 * Created by Skylar on 9/14/2014.
 */

module.exports = function(grunt) {
	'use strict';

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		meta: {
			banner: [
				'/**',
				' * <%= pkg.description %>',
					' * @version v<%= pkg.version %> - <%= grunt.template.today("yyyy-mm-dd") %>' +
					' * @link <%= pkg.homepage %>',
				' * @author <%= pkg.author %>',
				' * @license MIT License, http://www.opensource.org/licenses/MIT',
				' */'
			].join('\n')
		},
		bowerInstall: {
			install: {
				options: {
					targetDir: "./src/main/resources/libs",
					layout: "byType"
				}
			}
		}
	});

	grunt.loadNpmTasks('grunt-bower-task');

	grunt.renameTask('bower', 'bowerInstall');

	grunt.registerTask('default', ['build']);

	grunt.registerTask('build', ['bowerInstall']);

	grunt.registerTask('bump', 'Increment version number', function() {
		var versionType = grunt.option('type');
		function bumpVersion(version, versionType) {
			var type = {patch: 2, minor: 1, major: 0},
				parts = version.split('.'),
				idx = type[versionType || 'patch'];
			parts[idx] = parseInt(parts[idx], 10) + 1;
			while(++idx < parts.length) { parts[idx] = 0; }
			return parts.join('.');
		}
		var version;
		function updateFile(file) {
			var json = grunt.file.readJSON(file);
			version = json.version = bumpVersion(json.version, versionType || 'patch');
			grunt.file.write(file, JSON.stringify(json, null, '  '));
		}
		updateFile('package.json');
		updateFile('bower.json');
		grunt.log.ok('Version bumped to ' + version);
	});
}