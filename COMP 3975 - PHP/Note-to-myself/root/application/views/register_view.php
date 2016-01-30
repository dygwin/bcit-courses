<div class="jumbotron">
  <h1>Register</h1>  
</div>
<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<?php echo validation_errors(); ?>
		<?php echo form_open('register/verify_register', 'data-toggle="validator"'); ?>
			<div class="form-group">
				<label for='username'>Email Address: </label>
				<input type='email' class="form-control" data-error="That email address is invalid" name='username' id='username' required></input>
				<div class="help-block with-errors"></div>
	  		</div>
			<div class="form-group">
				<label for='password'>Password: </label>
				<input type='password' class="form-control" name='password' id='password' data-minlength="5" data-maxlength="12" required title="Password must be 5 to 12 characters" required></input>
				<span class="help-block">Password must be 5 to 12 characters</span>
	  		</div>
			<div class="form-group">
				<label for='password_confirmation'>Confirm Password: </label>
				<input type='password' class="form-control" data-match="#password" data-match-error="Whoops, these don't match" name='password_confirmation' id='password_confirmation' required></input>
				<div class="help-block with-errors"></div>
			</div>
			<?php echo $captcha['image']; ?>
			<div class="form-group">
				<label for='captcha'>Captcha: </label>
				<input type='text' class="form-control" name='captcha' id='captcha' required></input>
			</div>
			<button class="btn btn-primary" type='submit' >Register</button>
		</form>
		<?php echo anchor('login', 'Log In'); ?>
	</div>
</div>
