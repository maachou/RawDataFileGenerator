------------
DESCRIPTION:
------------

RawDataFileGenerator is a command line tool I used in one of my projects to generate test data that have a specific format.
The row definition is specified within an xml file that is passed as an input parameter.
A row contains a list of field that can be:
- NUM : Numerical values.
- ALPHA: Alphabetical values.
- ALPHANUM: Alphanumeric values
- DOMAIN_YES_NO : "YES"/"NO" string values.
- DOMAIN_GENDER : "Male"/"Female" string values.

 ------ 
 USAGE:
 ------
 
 java -jar target/RawDataFileGenerator.one-jar.jar -s /path/to/rowSpecFile.xml
 
 -----------------------------------
 Row specification xml file example:
 -----------------------------------
 
 <data>
	<totalrecords>50</totalrecords>
	<fields>
		<field>
			<order>1</order>
			<name>field_1</name>
			<description>Description field 1</description>
			<length>10</length>
			<type>ALPHA</type>
			<genproperties>
				<valuepresence>always</valuepresence> 
				<!-- always/ random / absent -->
				<datalength>variable</datalength> 
				<!-- none/full/variable -->
			</genproperties> 
		</field>
		<field>
			<order>2</order>
			<name>field_2</name>
			<description>Description field 2</description>
			<length>10</length>
			<type>ALPHANUM</type>
			<genproperties>
				<valuepresence>random</valuepresence> 
				<datalength>full</datalength> 
			</genproperties> 
		</field>
		<field>
			<order>3</order>
			<name>field_3</name>
			<description>Description field 3</description>
			<length>10</length>
			<type>DOMAIN_YES_NO</type>
			<genproperties>
				<valuepresence>random</valuepresence> 
			</genproperties> 
		</field>		
		<field>
			<order>4</order>
			<name>field_4</name>
			<description>Description field 4</description>
			<length>10</length>
			<type>DOMAIN_GENDER</type>
			<genproperties>
				<valuepresence>random</valuepresence> 
			</genproperties> 
		</field>				
		<field>
			<order>5</order>
			<name>field_5</name>
			<description>Description field 5</description>
			<length>10</length>
			<type>NUM</type>
			<genproperties>
				<valuepresence>random</valuepresence> 
			</genproperties> 
		</field>						
	</fields>
</data>
 