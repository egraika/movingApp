module.exports = function(grunt) {

    grunt.loadNpmTasks("grunt-contrib-uglify")
    grunt.loadNpmTasks("grunt-contrib-watch")
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-ng-annotate');

    grunt.initConfig({
        pkg: grunt.file.readJSON("package.json"),

        uglify: {
            t1: {
                files: {
                    'src/main/resources/static/js/dependencies/angular-2.min.js' : ['src/main/resources/static/js/jquery.js','src/main/resources/static/js/angular.min.js',
                    'src/main/resources/static/js/angular-route.js', 'src/main/resources/static/routing.js','src/main/resources/static/js/dependencies/http-auth-interceptor.js',
                    'src/main/resources/static/js/controllers/*', 'src/main/resources/static/js/services/*.js']
                }
            }
        }
    })


    grunt.registerTask("default", ["uglify", "watch", 'ngAnnotate', 'concat'])
}
