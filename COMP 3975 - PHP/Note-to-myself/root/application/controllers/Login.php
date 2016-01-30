<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends CI_Controller {
	
	public function __construct() {
        parent::__construct();
        
        $this->load->model('user','',TRUE);
		$this->load->helper('form');
		$this->load->helper('cookie');
    }

	public function index() {
		if (!isset($_SESSION['logged_in'])) {
   			$this->load->view('templates/header');
   			$this->load->view('login_view');
   			$this->load->view('templates/footer');
		} else {
			redirect('home', 'refresh');
		}
	}
	
	function verify_login() {
		if (!isset($_SESSION['logged_in'])) {
   			$this->load->library('form_validation');
 
			$this->form_validation->set_rules('username', 'Email Address', 'trim|required|valid_email');
			$this->form_validation->set_rules('password', 'Password', 'trim|required|callback_check_database');
		 
			if($this->form_validation->run() == FALSE) {
				//Field validation failed.  User redirected to login page
				
	   			$this->load->view('templates/header');
	   			$this->load->view('login_view');
	   			$this->load->view('templates/footer');
			} else {
				set_cookie('username', $this->input->post('username'), 5184000);
			 	redirect('home', 'refresh');
			}
		} else {
			redirect('home', 'refresh');
		}
		
	}
	
	function check_database($password) {
        //Field validation succeeded.  Validate against database
        $username = $this->input->post('username');
 
	    //query the database
	    $result = $this->user->login(strtolower($username), $password);
 
        if($result == 1) {
        	$this->form_validation->set_message('check_database', 'Invalid email or password');
		 	return false;
        } else if ($result == 2) {
        	$this->form_validation->set_message('check_database', 'Account not activated yet.');
		 	return false;
        } else if ($result == 3) {
        	$this->form_validation->set_message('check_database', 'Too many failed attempts. A new password has been sent to your email.');
        } else if ($result) {
            $sess_array = array();
     		foreach($result as $row) {
       			$sess_array = array('id' => $row->id,
         						    'username' => $row->username
       								);
       			$this->session->set_userdata('logged_in', $sess_array);
     		}
     		return TRUE;
	   	}
 	}
}
