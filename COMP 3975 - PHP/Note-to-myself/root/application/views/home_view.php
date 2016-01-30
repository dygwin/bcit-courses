<div class='jumbotron'>
        <h1><?php if (isset($username)) echo $username; ?></h1>
        <?php echo anchor('home/logout', 'Logout', 'id="logout"'); ?>
</div>

<div class="container">
<?php echo form_open_multipart('home/update'); ?>
<div class='row'>
    <div class='col-sm-3 column' id='notes'>
        <h3><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Notes</h3>
        <textarea name='notes' id='notes' rows='20'><?php if (isset($note)) echo $note; ?></textarea>
    </div>
    <div class='col-sm-3 column' id='websites'>
        <h3><span class="glyphicon glyphicon-globe" aria-hidden="true"></span> Websites</h3>
        <?php
            if (isset($websites))
                foreach ($websites as $website)
                    echo "<input type='text' name='websites[]' value='". $website . "' onclick='openInNew(this);'/>"
        ?>
        <input type='text' name='websites[]' value='' onclick='openInNew(this);'/>
        <input type='text' name='websites[]' value='' onclick='openInNew(this);'/>
        <input type='text' name='websites[]' value='' onclick='openInNew(this);'/>
        <input type='text' name='websites[]' value='' onclick='openInNew(this);'/>
    </div>
    <div class='col-sm-3 column' id='images'>
        <h3><span class="glyphicon glyphicon-picture" aria-hidden="true"></span> Images</h3>
        <?php
            if (isset($image_error))
                echo "<h5>$image_error</h5>";
        
            if ($img_count < 4) {
                echo "<input type='file' name='image' accept='image/jpeg,image/gif' size='20' /></br>";
            } else {
                echo "<h5>Maximum image upload limit reached.</h5>";
            }
        ?>
        <?php
            if (isset($images)) {
                $i = 0;
                foreach ($images as $image) {
                    echo "<a href='#' data-toggle='modal' data-target='#modal" . $i . "'><img src='" . $image["thumb"] . "' ></img></a>
                          <input type='checkbox' name='image" . $i++ . "' value='" . $image['id'] . "'> Delete
                          </br></br>";
                }
            }
        ?>
    </div>
    <div class='col-sm-3 column' id='tbd'>
        <h3><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> TBD</h3>
        <textarea name='tbd' id='tbd' rows='20'><?php if (isset($tbd)) echo $tbd; ?></textarea>
    </div>
</div>
<div class='row'>
    <div class='col-xs-12'>
        <button type="submit" class="btn btn-primary">Save</button>
    </div>
</div>
</form>
<?php
if (isset($images)) {
    $i = 0;
    foreach ($images as $image) {
        echo "
        <div id=modal" . $i++ ." class='modal fade' role='dialog'>
            <div class='modal-dialog'>

                <!-- Modal content-->
                <div class='modal-content'>
                  <div class='modal-body text-center'>
                    <img src='" . $image["src"] . "' width='100%'></img>
                  </div>
                  <div class='modal-footer'>
                    <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
                  </div>
                </div>

            </div>
        </div>";
    }
}
?>
</div>
