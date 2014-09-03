name := "Notes2"

version := "1.0"

libraryDependencies ++= Seq(
	jdbc,
	anorm,
	"postgresql" % "postgresql" % "9.1-901.jdbc4",
	"com.github.t3hnar" %% "scala-bcrypt" % "2.4"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)