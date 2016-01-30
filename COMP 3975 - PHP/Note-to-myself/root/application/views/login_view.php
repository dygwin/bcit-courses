<div class="jumbotron">
  <h1>Login</h1>  
</div>
<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<?php echo validation_errors(); ?>
		<?php echo form_open('login/verify_login'); ?>
			<div class="form-group">
				<label for='username'>Email Address: </label>
				<input type='email' class="form-control" name='username' id='username' value="<?php echo get_cookie('username'); ?>" required></input>
			</div>
			<div class="form-group">
				<label for='password'>Password: </label>
				<input type='password' class="form-control" name='password' id='password' required></input>
			</div>
			<button type='submit' class="btn btn-primary">Log In</button>
		</form>
		<?php echo anchor('reset', 'Forgot your password?', 'style="margin-right:5px;"'); ?>
		<?php echo anchor('register', 'Register'); ?>
	</div>
</div>
<script>
var password = document.getElementById("password")
  , confirm_password = document.getElementById("password_confirmation");

function validatePassword(){
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
</script>
