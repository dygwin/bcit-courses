
Configuration to run this on your own server:
	Place contents of project_root into your web root.

	Run the SQL script to create the tables:
		database.sql
	Configure database settings:
		application/config/database.php
	Configure baseurl:
		application/config/database.php
	Give proper wr permissions to:
		assets/images
		assets/captcha
	Configure the .htaccess for your server domain:
		assets/images/.htaccess