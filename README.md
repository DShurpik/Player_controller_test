mvn clean test -DsuiteXml="PositiveTests" -Dconfig="positiveTestValues" 
для запуска тестов из suit ApiTest.xml и конфигурацией positiveTestValues.properties для позитивных сценариев

mvn clean test -DsuiteXml="PositiveTests" -Dconfig="positiveTestValues" allure:serve
для создания отчетов аллюр в конце прогона

mvn clean test -DsuiteXml="NegativeTests" -Dconfig="negativeTestValues"
для запуска тестов из suit ApiTest.xml и конфигурацией negativeTestValues.properties для негативных сценариев

mvn clean test -DsuiteXml="NegativeTests" -Dconfig="negativeTestValues" allure:serve
для создания отчетов аллюр в конце прогона