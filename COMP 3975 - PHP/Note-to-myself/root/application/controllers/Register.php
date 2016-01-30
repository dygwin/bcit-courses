<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Register extends CI_Controller {
	
	public function __construct() {
        parent::__construct();
        
        $this->load->model('user','',TRUE);
        $this->load->model('mailer','',TRUE);
		$this->load->helper('form');
		$this->load->helper('captcha');
		$this->load->helper('cookie');
    }

	public function index() {
		if (!isset($_SESSION['logged_in'])) {
			// Generate a new captcha
			$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
			$charactersLength = strlen($characters);
			$randomString = '';
			for ($i = 0; $i < 5; $i++) {
				$randomString .= $characters[rand(0, $charactersLength - 1)];
			}
					
			$vals = array(
		    'word'          => $randomString,
		    'img_path'      => './assets/captcha/',
		    'img_url'       => base_url('assets/captcha/'),
		    'font_path'     => './assets/captcha/times_new_yorker.ttf',
		    'img_width'     => '150',
		    'img_height'    => 30,
		    'expiration'    => 60,
		    'word_length'   => 8,
		    'font_size'     => 16,
		    'img_id'        => 'Imageid',
		    'pool'          => '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ',

		    // White background and border, black text and red grid
		    'colors'        => array(
		            'background' => array(255, 255, 255),
		            'border' => array(255, 255, 255),
		            'text' => array(0, 0, 0),
		            'grid' => array(255, 40, 40)
		      )
			);
			
			$cap = create_captcha($vals);
			
			$_SESSION["word"] = $cap["word"];
			
			$this->load->view('templates/header');
   			$this->load->view('register_view', array("captcha" => $cap));
			$this->load->view('templates/footer');
		} else {
			redirect('home', 'refresh');
		}
	}
	
	function verify_register() {
		if (!isset($_SESSION['logged_in'])) {
			$this->load->library('form_validation');
		
			if (strtolower($this->input->post('captcha')) != strtolower($_SESSION["word"])) {
				$this->form_validation->set_message('check_database', 'Incorrect Captcha.');
			
				// Generate a new password
				$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
				$charactersLength = strlen($characters);
				$randomString = '';
				for ($i = 0; $i < 5; $i++) {
					$randomString .= $characters[rand(0, $charactersLength - 1)];
				}
					
				$vals = array(
				'word'          => $randomString,
				'img_path'      => './assets/captcha/',
				'img_url'       => base_url('assets/captcha/'),
				'font_path'     => './assets/captcha/times_new_yorker.ttf',
				'img_width'     => '150',
				'img_height'    => 30,
				'expiration'    => 60,
				'word_length'   => 8,
				'font_size'     => 16,
				'img_id'        => 'Imageid',
				'pool'          => '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ',

				// White background and border, black text and red grid
				'colors'        => array(
				        'background' => array(255, 255, 255),
				        'border' => array(255, 255, 255),
				        'text' => array(0, 0, 0),
				        'grid' => array(255, 40, 40)
				  )
				);
			
				$cap = create_captcha($vals);
			
				$_SESSION["word"] = $cap["word"];
			
			
				$this->load->view('templates/header');
	   			$this->load->view('register_view', array("captcha" => $cap));
				$this->load->view('templates/footer');
			 	return;
			}
	 
			$this->form_validation->set_rules('username', 'Email Address', 'trim|required|valid_email');
			$this->form_validation->set_rules('password', 'Password', 'trim|required|min_length[5]|max_length[12]');
			$this->form_validation->set_rules('password_confirmation', 'Password Confirmation', 'trim|required|min_length[5]|max_length[12]|matches[password]|callback_check_database');
		 
			if($this->form_validation->run() == FALSE) {
			 	//Field validation failed.  User redirected to login page
			 	// Generate a new captcha
				$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
				$charactersLength = strlen($characters);
				$randomString = '';
				for ($i = 0; $i < 5; $i++) {
					$randomString .= $characters[rand(0, $charactersLength - 1)];
				}
					
				$vals = array(
				'word'          => $randomString,
				'img_path'      => './assets/captcha/',
				'img_url'       => base_url('assets/captcha/'),
				'font_path'     => './assets/captcha/times_new_yorker.ttf',
				'img_width'     => '150',
				'img_height'    => 30,
				'expiration'    => 60,
				'word_length'   => 8,
				'font_size'     => 16,
				'img_id'        => 'Imageid',
				'pool'          => '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ',

				// White background and border, black text and red grid
				'colors'        => array(
				        'background' => array(255, 255, 255),
				        'border' => array(255, 255, 255),
				        'text' => array(0, 0, 0),
				        'grid' => array(255, 40, 40)
				  )
				);
			
				$cap = create_captcha($vals);
			
				$_SESSION["word"] = $cap["word"];
			
			
				$this->load->view('templates/header');
	   			$this->load->view('register_view', array("captcha" => $cap));
				$this->load->view('templates/footer');
			} else {
				set_cookie('username', $this->input->post('username'), 5184000);
				
			 	$this->mailer->send_activation_code($this->input->post('username'), $_SESSION["activation_code"]);
			 	
				$this->load->view('templates/header');
			 	$this->load->view("registration_success", array("code" => $_SESSION["activation_code"]));
				$this->load->view('templates/footer');
		   	}
		} else {
			redirect('home', 'refresh');
		}
	}
	
	function check_database($password) {
        //Field validation succeeded.  Validate against database
        $username = $this->input->post('username');
 
	    //query the database
	    $result = $this->user->register(strtolower($username), $password);
 
        if($result) {
        	$_SESSION["activation_code"] = $result;
        	
        	mkdir('./assets/images/' . $_SESSION['id'], 0777, TRUE);
        	copy('./assets/images/.htaccess', './assets/images/' . $_SESSION['id'] . '/.htaccess');
        	
     		return TRUE;
	   	} else {
		 	$this->form_validation->set_message('check_database', 'Email already exists.');
		 	return false;
	   	}
 	}
}
