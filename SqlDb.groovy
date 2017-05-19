import groovy.sql.Sql


@Grab('mysql:mysql-connector-java:5.1.38')
@GrabConfig(systemClassLoader=true)


def url = 'jdbc:mysql://localhost/test'
def user = 'test'
def password = 'test'
def driver = 'com.mysql.jdbc.Driver'
def sql = Sql.newInstance(url, user, password, driver)

sql.query('select * from TestTable') { result ->

        while(result.next()) {

                def id = result.getInt('id')
                def name = result.getString('name')
                def age = result.getInt('age')

                println id + ' ' + name + ' ' + age

        }
}

sql.eachRow("select * from TestTable") { row -> 
	
	def id =  row.id
	def name = row.name
	def age = row.age
	
	println id +  ' ' + name + ' ' + age	


}

