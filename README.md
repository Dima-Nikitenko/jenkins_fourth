Steps to launch tests:
1. Launch the following command in the root directory of the entire project: mvn install -DskipTests
2. Launch the following command in the {project root}/onliner-cucumber directory: mvn test 

Allure and cucumber reports are generated in: {project root}/test-reports.
To see Allure reports, launch the following command in the {project root}/test-reports directory (Scoop commandline-installer and Allure must be installed in the OS): allure serve
