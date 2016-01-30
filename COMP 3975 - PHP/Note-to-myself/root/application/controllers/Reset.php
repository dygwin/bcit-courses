<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Reset extends CI_Controller {
	
	public function __construct() {
        parent::__construct();
        
        $this->load->model('user','',TRUE);
    }

	public function index() {
		if (!isset($_SESSION['logged_in'])) {
			$this->load->helper('form');
			
			$this->load->view("templates/header");
			$this->load->view("reset_view");
			$this->load->view("templates/footer");
		} else {
			redirect('home', 'refresh');
		}
	}
	
	function do_reset() {
		if (!isset($_SESSION['logged_in'])) {
			$this->load->library('form_validation');
	 
			$this->form_validation->set_rules('username', 'Email Address', 'trim|required|valid_email');
		 
			if($this->form_validation->run() == FALSE) {
				//Field validation failed.  User redirected to login page
				$this->load->view("templates/header");
				$this->load->view("reset_view");
				$this->load->view("templates/footer");
			} else {
				$username = $this->input->post('username');
			
			 	$newpass = $this->user->reset_password($username);
			
				$this->load->view("templates/header");
				$this->load->view('reset_success', array("password" => $newpass));
				$this->load->view("templates/footer");
		   	}
		} else {
			redirect('home', 'refresh');
		}
	}
}
