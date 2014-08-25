name := "Notes2"

version := "1.0"

libraryDependencies ++= Seq(
	jdbc,
	"org.xerial" % "sqlite-jdbc" % "3.7.15-M1"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)