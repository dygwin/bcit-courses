<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Home extends CI_Controller {
	
	public function __construct() {
        parent::__construct();
        $this->load->helper('form');
    }

	public function index() {
		if ($this->session->userdata('logged_in')) {
 			$this->load->model('user');
 			
 			$data = $this->user->get_viewable($_SESSION['logged_in']['id']);
 			$data['username'] = $_SESSION['logged_in']['username'];
 			
 			if (isset($_SESSION['image_error'])) {
 				$data['image_error'] = $_SESSION['image_error'];
 			}
			
			$this->load->view('./templates/header');
			$this->load->view('home_view', $data);
			$this->load->view('./templates/footer');
		} else {
			redirect('login', 'refresh');
		}
	}
	
	function logout() {
		if ($this->session->userdata('logged_in')) {
			$files = glob('./assets/images/' . $_SESSION['logged_in']['id'] . '/*'); // get all file names
			
			foreach($files as $file){ // iterate files
			  	if(is_file($file)) {
					unlink($file);
				}
			}

	   		$this->session->unset_userdata('logged_in');
	   		session_destroy();
	   		redirect('login', 'refresh');
	   	} else {
	   		redirect('login', 'refresh');
	   	}
 	}
 	
 	function update() {
 		if (isset($_SESSION['logged_in'])) {
 			$this->load->model('user');
   			$this->load->library('form_validation');
 
			$this->form_validation->set_rules('notes', 'Notes', 'trim');
			$this->form_validation->set_rules('tbd', 'TBD', 'trim');
			$this->form_validation->set_rules('websites[]', 'Website', 'trim');
		 
			if($this->form_validation->run() == FALSE) {
				//Field validation failed.  User redirected to login page
			 	redirect('home', 'refresh');
			} else {
				$this->user->update_notes($_SESSION['logged_in']['id'], $this->input->post('notes'));
				$this->user->update_tbd($_SESSION['logged_in']['id'], $this->input->post('tbd'));
				$this->user->update_websites($_SESSION['logged_in']['id'], $this->input->post('websites'));
				
				if (isset($_FILES['image'])) {
					if ($_FILES['image']['size'] > 2 * 1048576) {
						$this->session->set_flashdata('image_error', 'Image must a gif or jpeg under 2MB');
					} else if ($_FILES['image']['size'] > 0) {
						$tmpName = $_FILES['image']['tmp_name'];
						$type = $_FILES['image']['type'];
						
						if ($type == "image/jpeg") {
							if ($i = @imagecreatefromjpeg($_FILES['image']['tmp_name'])) {
								imagedestroy($i);
								
								$fp = fopen($tmpName, 'r');

								$data = fread($fp, filesize($tmpName));
								$data = addslashes($data);

								fclose($fp);
					
								$this->user->add_image($_SESSION['logged_in']['id'], $data, $type);
							} else {
								$this->session->set_flashdata('image_error', 'Image must a gif or jpeg under 2MB');
							}
						} else if ($type == "image/gif") {
							if ($i = @imagecreatefromgif($_FILES['image']['tmp_name'])) {
								imagedestroy($i);
								
								$fp = fopen($tmpName, 'r');

								$data = fread($fp, filesize($tmpName));
								$data = addslashes($data);

								fclose($fp);
					
								$this->user->add_image($_SESSION['logged_in']['id'], $data, $type);
							} else {
								$this->session->set_flashdata('image_error', 'Image must a gif or jpeg under 2MB');
							}
						} else {
							$this->session->set_flashdata('image_error', 'Image must a gif or jpeg under 2MB');
						}
					}
				}
				
				if ($this->input->post('image0') != null) {
					$this->user->remove_image($_SESSION['logged_in']['id'], $this->input->post('image0'));
				}
				
				if ($this->input->post('image1') != null) {
					$this->user->remove_image($_SESSION['logged_in']['id'], $this->input->post('image1'));
				}
				
				if ($this->input->post('image2') != null) {
					$this->user->remove_image($_SESSION['logged_in']['id'], $this->input->post('image2'));
				}
				
				if ($this->input->post('image3') != null) {
					$this->user->remove_image($_SESSION['logged_in']['id'], $this->input->post('image3'));
				}
				
			 	redirect('home', 'refresh');
			}
		} else {
			redirect('login', 'refresh');
		}
 	}
}
