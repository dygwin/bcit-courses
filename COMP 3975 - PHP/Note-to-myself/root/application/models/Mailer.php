<?php
require_once('vendor/autoload.php');
require_once('vendor/swiftmailer/swiftmailer/lib/swift_required.php');

date_default_timezone_set("America/Vancouver");

Class mailer extends CI_Model {
	function send_activation_code($email, $activation_code) {
		$transporter = new Swift_SmtpTransport('smtp.gmail.com', 465, 'ssl');
		$transporter->setUsername('');
		$transporter->setPassword('');
		
		$message = new Swift_Message($transporter);
		$message->setTo(array($email => $email));
		$message->setSubject("Note to Myself - Activation Link");
		$message->addPart("Thanks for making an account!</br>Click <a href='http://localhost/activate/code/"
							. $activation_code . "'>this link</a> to activate your account now!", 'text/html');
		$message->setFrom("", "");
		$mailer = new Swift_Mailer($transporter);
		$mailer->send($message);
	}
	
	function send_password_reset($email, $password) {
		$transporter = new Swift_SmtpTransport('smtp.gmail.com', 465, 'ssl');
		$transporter->setUsername('');
		$transporter->setPassword('');
		
		$message = new Swift_Message($transporter);
		$message->setTo(array($email => $email));
		$message->setSubject("Note to Myself - Password Reset");
		$message->addPart("Your password has been reset. </br>Your new password is <strong>$password</strong>", 'text/html');
		$message->setFrom("", "");
		
		$mailer = new Swift_Mailer($transporter);
		$mailer->send($message);
	}
	
	function send_breakin_alert($email, $password) {
		$transporter = new Swift_SmtpTransport('smtp.gmail.com', 465, 'ssl');
		$transporter->setUsername('');
		$transporter->setPassword('');
		
		$message = new Swift_Message($transporter);
		$message->setTo(array($email => $email));
		$message->setSubject("Note to Myself - Break-in Attempt");
		$message->addPart("Your password has been reset after 3 failed log-in attempts.</br>Your new password is <strong>$password</strong>", 'text/html');
		$message->setFrom("", "");
		
		$mailer = new Swift_Mailer($transporter);
		$mailer->send($message);
	}
}
?>
