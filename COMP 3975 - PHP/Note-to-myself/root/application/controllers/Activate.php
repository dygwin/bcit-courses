<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Activate extends CI_Controller {
	
	public function __construct() {
        parent::__construct();
        
        $this->load->model('user','',TRUE);
    }

	public function index() {
		show_404();
	}
	
	function code($code) {
	    if(!empty($code) && strlen($code) == 30) {
		 	if ($this->user->activate($code)) {
		 		$this->load->view('templates/header');
		 		$this->load->view('activated_view');
		 		$this->load->view('templates/footer');
		 	} else {
				show_404();
		 	}
	    } else {
			show_404();
	   }
	}
}
