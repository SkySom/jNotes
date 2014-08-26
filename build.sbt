name := "Notes2"

version := "1.0"

libraryDependencies ++= Seq(
	jdbc,
	"postgresql" % "postgresql" % "9.1-901.jdbc4"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)