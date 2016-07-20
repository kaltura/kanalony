logLevel := Level.Warn

resolvers += Resolver.jcenterRepo
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.1")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.0")