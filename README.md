# robotframework-httplibrary-java

HTTP keyword library for RobotFramework in Java

This is an initial POC...stay tuned for updates.

## Usage

1. Use robotframework-maven-plugin in your test project's build section. See http://robotframework.org/MavenPlugin/ for more details.
2. Configure the plugin to add this project as a dependency:

        <dependencies>
          <dependency>
            <groupId>net.randomsync</groupId>
            <artifactId>robotframework-httplibrary-java</artifactId>
            <version>0.1.0</version>
          </dependency>
        </dependencies>

3. Import this library in your tests:

        | *** Settings ***
        | Library    | HTTPLibrary

4. Add Robot Framework style tests under src/test/robotframework/acceptance
5. Run `mvn verify`.

Detailed instructions coming soon...
