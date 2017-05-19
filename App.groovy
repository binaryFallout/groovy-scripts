def cli = new CliBuilder(usage:'mvn-to-gradle <filepath>')

cli.b 'print beautiful'
cli.h(longOpt: 'help', 'print this message')
cli.v(longOpt: 'version', 'print version')

def options = cli.parse(args)

println ""
println "Maven to Gradle Dependency Converter"

if(args.length == 0) {
	cli.usage()
	return
}

if(options.v) {
	println "0.1"
	return	
}

File mvnFile = new File(options.arguments()[0])
if(!mvnFile.exists()) {
	println "File not found\n"
	return
}

def project  = new XmlSlurper().parse(mvnFile)
def dependencies = project.dependencies

println "" 
println "Gradle Dependencies:"
println ""
dependencies.children().each { dependency ->
	
	if(options.b) {
		println "compile group: '" + dependency.groupId + "', name: '" + dependency.artifactId + "', version: '" + dependency.version + "'"
	} else {
		println "compile '" + dependency.groupId + ":" + dependency.artifactId + ":" + dependency.version + "'"
	}
}

println ""

