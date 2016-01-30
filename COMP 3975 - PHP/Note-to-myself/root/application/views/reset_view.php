<div class="jumbotron">
  <h1>Reset Password</h1>  
</div>
<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<?php echo validation_errors(); ?>
		<?php echo form_open('reset/do_reset'); ?>
			<div class='form-group'>
				<label for='username'>Email Address: </label>
				<input type='email' class='form-control' name='username' id='username' required></input>
			</div>
			<button type='submit' class='btn btn-primary'>Reset</button>
		</form>
		<?php echo anchor('login', 'Log In', 'style="margin-right:5px;"'); ?>
		<?php echo anchor('register', 'Register'); ?>
	</div>
</div>
