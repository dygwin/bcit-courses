<?php
Class user extends CI_Model {
	function login($username, $password) {
		$this -> db -> select('id, username, password, active');
		$this -> db -> from('users');
		$this -> db -> where('username', $username);
		$this -> db -> where('password', MD5($password));
	 	$this -> db -> limit(1);
		 
		$query = $this -> db -> get();
		 
		if($query -> num_rows() == 1) {
			// Reset the failed login attempts
			$this->db->where('username', $username);
			$this->db->where('password', MD5($password));
			$this->db->update('users', array(
					'failed_attempts' => 0
			));
			
			if ($query->row()->active == 1) {
				return $query->result();
			} else {
				return 2;
			}
		} else {
			// Increment failed log in attempts for that username
			$this -> db -> select('failed_attempts');
			$this -> db -> from('users');
			$this -> db -> where('username', $username);
		 	$this -> db -> limit(1);
		 
			$query = $this -> db -> get();
			
			if($query -> num_rows() == 1) {
				$attempts = $query->row()->failed_attempts;
				
				$this->db->where('username', $username);
				$this->db->update('users', array(
						'failed_attempts' => $attempts + 1
				));
				
				// We're about to hit 3 attempts, reset password and send email
				if ($attempts == 2) {
					// Generate a new password
					$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
					$charactersLength = strlen($characters);
					$randomString = '';
					for ($i = 0; $i < 10; $i++) {
						$randomString .= $characters[rand(0, $charactersLength - 1)];
					}
					
					$this->db->where('username', $username);
					$this->db->update('users', array(
							'password' => md5($randomString)
					));
					
					$this->load->model('mailer');
					$this->mailer->send_breakin_alert($username, $randomString);
					
					return 3;
				}
			}
			
			return 1;
		}
	}
	
	function register($username, $password) {
		$this -> db -> select('id, username, password');
		$this -> db -> from('users');
		$this -> db -> where('username', $username);
	 	$this -> db -> limit(1);
		 
		$query = $this -> db -> get();
		 
		if($query -> num_rows() == 0) {
			// Generate a random activation code
			$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    		$charactersLength = strlen($characters);
    		$randomString = '';
    		for ($i = 0; $i < 30; $i++) {
        		$randomString .= $characters[rand(0, $charactersLength - 1)];
    		}
    		
			$data = array(
				'username' => $username,
				'password' => md5($password),
				'activation_code' => $randomString
				);

			$this->db->insert('users', $data);
			
			$this -> db -> select('id');
			$this -> db -> from('users');
			$this -> db -> where('username', $username);
		 	$this -> db -> limit(1);
			 
			$query = $this -> db -> get();
			$row = $query->row();
			
			if (isset($row)) {
				$this->session->set_flashdata('id', $row->id);
			}
    		
			return $randomString;
		} else {
			return false;
		}
	}
	
	function activate($activation_code) {
		$data = array(
				'active' => 1
		);

		$this->db->where('activation_code', $activation_code);
		$result = $this->db->update('users', $data);
		 
		if($result) {
			return true;
		} else {
			return false;
		}
	}
	
	function reset_password($username) {
		// Generate a new password
		$characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
		$charactersLength = strlen($characters);
		$randomString = '';
		for ($i = 0; $i < 10; $i++) {
			$randomString .= $characters[rand(0, $charactersLength - 1)];
		}
		
		$this->db->where('username', $username);
		$this->db->update('users', array(
				'password' => md5($randomString)
		));
		
		$this->load->model('mailer');
		$this->mailer->send_password_reset($username, $randomString);
		
		return $randomString;
	}
	
	function update_notes($id, $notes) {
		$this -> db -> select('id, user_id, text');
		$this -> db -> from('notes');
		$this -> db -> where('user_id', $id);
	 	$this -> db -> limit(1);
		 
		$query = $this -> db -> get();
		 
		if($query -> num_rows() == 0) {
			$data = array(
				'id' => 'null',
				'user_id' => $id,
				'text' => $notes
			);

			$this->db->insert('notes', $data);
		} else {
			$data = array(
				'text' => $notes
			);

			$this->db->where('user_id', $id);
			$result = $this->db->update('notes', $data);
		}
	}
	
	function update_tbd($id, $tbd) {
		$this -> db -> select('id, user_id, text');
		$this -> db -> from('tbd');
		$this -> db -> where('user_id', $id);
	 	$this -> db -> limit(1);
		 
		$query = $this -> db -> get();
		 
		if($query -> num_rows() == 0) {
			$data = array(
				'id' => 'null',
				'user_id' => $id,
				'text' => $tbd
			);

			$this->db->insert('tbd', $data);
		} else {
			$data = array(
				'text' => $tbd
			);

			$this->db->where('user_id', $id);
			$result = $this->db->update('tbd', $data);
		}
	}
	
	function update_websites($id, $websites) {
		$this->db->delete('websites', array('user_id' => $id));
		 
		foreach ($websites as $website) {
			if (!empty($website)) {
				$data = array(
					'id' => 'null',
					'user_id' => $id,
					'url' => $website
				);
			
				$this->db->insert('websites', $data);
			}
		} 
	}
	
	function get_viewable($id) {
		$data = array();
		
		// get note
		$this -> db -> select('text');
		$this -> db -> from('notes');
		$this -> db -> where('user_id', $id);
	 	$this -> db -> limit(1);
		 
		$query = $this -> db -> get();
		
		$row = $query->row();
		 
		if(isset($row)) {
			$data['note'] = $row->text;
		}
		
		// get tbd
		$this -> db -> select('text');
		$this -> db -> from('tbd');
		$this -> db -> where('user_id', $id);
	 	$this -> db -> limit(1);
		 
		$query = $this -> db -> get();
		
		$row = $query->row();
		 
		if(isset($row)) {
			$data['tbd'] = $row->text;
		}
		
		// get websites
		$this -> db -> select('url');
		$this -> db -> from('websites');
		$this -> db -> where('user_id', $id);
		 
		$query = $this -> db -> get();
		
		$websites = array();
		
		foreach ($query->result_array() as $row) {
			array_push($websites, $row['url']);
		}
		 
		if($websites) {
			$data['websites'] = $websites;
		}
		
		// get images
		$this -> db -> select('id, image, type');
		$this -> db -> from('images');
		$this -> db -> where('user_id', $id);
		 
		$query = $this -> db -> get();
		
		$data['img_count'] = $query->num_rows();
		
		$images = array();
		
		foreach ($query->result_array() as $row) {
			if ($row['type'] == 'image/jpeg') {
				if (!file_exists('./assets/images/'. $_SESSION['logged_in']['id']
								  . '/' . $row['id'] . '.jpeg')) {
					$im = imagecreatefromstring(stripslashes($row['image']));
					imagejpeg($im, './assets/images/' . $id . '/' . $row['id'] . '.jpeg');
					
					$original_x = imagesx($im);
					$original_y = imagesy($im);
					$new_x = 0;
					$new_y = 0;
					
					if ($original_x > $original_y) {
						$new_x = 125;
						$new_y = round(125 * $original_y / $original_x);
					} else {
						$new_y = 125;
						$new_x = round(125 * $original_x / $original_y);
					}
					
					$thumbnail = imagecreatetruecolor($new_x, $new_y);
					
					imagecopyresampled($thumbnail, $im,
									   0, 0,             0,0,
									   $new_x, $new_y, $original_x, $original_y);
									   
					imagejpeg($thumbnail, './assets/images/' . $id . '/' . $row['id'] . '_thumb.jpeg');
					
					imagedestroy($im);
				}
				array_push($images, array('id' => $row['id'], 'src' => './assets/images/'. $_SESSION['logged_in']['id']
								  . '/' . $row['id'] . '.jpeg', 
										  'thumb' => './assets/images/'. $_SESSION['logged_in']['id']
								  . '/' . $row['id'] . '_thumb.jpeg'));
			} else if ($row['type'] == 'image/gif') {
				if (!file_exists('./assets/images/'. $_SESSION['logged_in']['id']
								  . '/' . $row['id'] . '.gif')) {
					$im = imagecreatefromstring(stripslashes($row['image']));
					imagegif($im, './assets/images/' . $id . '/' . $row['id'] . '.gif');
					
					$original_x = imagesx($im);
					$original_y = imagesy($im);
					$new_x = 0;
					$new_y = 0;
					
					if ($original_x > $original_y) {
						$new_x = 125;
						$new_y = round(125 * $original_y / $original_x);
					} else {
						$new_y = 125;
						$new_x = round(125 * $original_x / $original_y);
					}
					
					$thumbnail = imagecreatetruecolor($new_x, $new_y);
					
					imagecopyresampled($thumbnail, $im,
									   0, 0,             0,0,
									   $new_x, $new_y, $original_x, $original_y);
									   
					imagegif($thumbnail, './assets/images/' . $id . '/' . $row['id'] . '_thumb.gif');
					
					imagedestroy($im);
				}
    			array_push($images, array('id' => $row['id'], 'src' => './assets/images/'. $_SESSION['logged_in']['id']
								  . '/' . $row['id'] . '.gif', 
										  'thumb' => './assets/images/'. $_SESSION['logged_in']['id']
								  . '/' . $row['id'] . '_thumb.gif'));
			}
		}
		 
		if($images) {
			$data['images'] = $images;
		}
		
		if ($data) {
			return $data;
		} else {
			return false;
		}
	}
	
	function add_image($id, $image, $type) {
		$data = array(
			'id' => 'null',
			'user_id' => $id,
			'image' => $image,
			'type' => $type
		);
			
		$this->db->insert('images', $data);
	}
	
	function remove_image($user_id, $image_id) {
		$this->db->delete('images', array('user_id' => $user_id, 'id' => $image_id));
	}
}
?>
