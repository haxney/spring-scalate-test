/**
 * Copyright (C) 2009-2011 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import sbt._
import org.fusesource.scalate.sbt._

class Project(info: ProjectInfo) extends DefaultWebProject(info) with PrecompilerWebProject {

  lazy val fusesource_snapshot_repo = "FuseSource Snapshots" at "http://repo.fusesource.com/nexus/content/repositories/snapshots"
  lazy val java_net_repo = "Java.net Repository" at "http://download.java.net/maven/2"
  lazy val springMilestone = "Spring Framework Milestone Repository" at "http://maven.springframework.org/milestone"
  lazy val jbossRepository   = "jboss" at "https://repository.jboss.org/nexus/content/groups/public"
  lazy val neo_repo     = "Neo4j Maven Repository" at "http://m2.neo4j.org"

  lazy val scalateSpring = "org.fusesource.scalate"   % "scalate-spring-mvc" % "1.5.0-SNAPSHOT"
  lazy val servlet       = "javax.servlet"            % "servlet-api"        % "2.5"
  lazy val logback       = "ch.qos.logback"           % "logback-classic"    % "0.9.26"
  lazy val cglib         = "cglib"                    % "cglib"              % "2.2"
  // to get jetty-run working in sbt
  lazy val jetty_webapp  = "org.eclipse.jetty"        % "jetty-webapp"       % "7.0.2.RC0" % "test"
  lazy val spring        = "org.springframework"      % "spring-webmvc"      % "3.1.0.M1"
  lazy val neo           = "org.neo4j"                % "neo4j-kernel"       % "1.3-SNAPSHOT"
  lazy val data_graph    = "org.springframework.data" % "spring-data-neo4j"  % "1.0.0.M3"
  lazy val aspectj       = "org.aspectj"              % "aspectjrt"          % "1.6.11.M2"
  lazy val slf4j         = "org.slf4j"                % "jcl-over-slf4j"     % "1.6.1"
  lazy val commons_log   = "commons-logging"          % "commons-logging"    % "1.1.1"     % "provided"

  override def ivyXML =
    <dependencies>
      <exclude org="com.sun.jdmk"/>
      <exclude org="com.sun.jmx"/>
    </dependencies>
}
