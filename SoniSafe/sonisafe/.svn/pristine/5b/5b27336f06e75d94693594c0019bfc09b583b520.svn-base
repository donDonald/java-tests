<html>
<head>
<title>Admin</title>
</head>
<body>


<?php


//echo phpinfo();    
error_reporting(E_ALL);
//error_reporting(E_ERROR | E_WARNING | E_PARSE);
error_reporting(E_ERROR | E_PARSE);
ini_set('display_errors', 'on');

include_once "settings.php";

$shotsFolder = "/var/www/html/audioshots";
$resultFormUrl = "http://pavel.sonisafe.com/admin.php";
//$resultFormUrl = "http://localhost/html/admin.php";

if( $_SERVER['REQUEST_METHOD'] === 'POST' ) {
    if( $_POST['action'] == 'setExpectedResult' ) {
        setResultOptions( $_POST );
    } else if( $_POST['action'] == 'removeAudioShots' ) {
        removeAudioShots( $_POST[ 'shots' ] );
    } else {
        die ( 'unknownmethod' );
    }
}


function setResultOptions($result) {
    Settings::setPushEmptyData( $result[ 'pushEmptyData' ] );
    Settings::setExpectedResult( $result[ 'expectedResult' ] );
}

function getAudioShots() {
    $audioShots = array_diff( scandir( '/var/www/html/audioshots' ), array('..', '.'));
    foreach(  $audioShots as $shot ) {
        echo '<input type="checkbox" name="shots[]" value="' . $shot . '">' . $shot . '<br>';
    }
}

function removeAudioShots($shots) {
    foreach( $shots as $shot ) {
        unlink( '/var/www/html/audioshots/' . $shot );
    }
}

?>

<div>Result options:</div>
<form name="resut" action=<?php echo $resultFormUrl; ?>  method="POST">
<div>
<input type="hidden" name="action" value="setExpectedResult"/>
<input type="checkbox" name="pushEmptyData" <?php if( TRUE == Settings::getPushEmptyData() ) { echo 'value="TRUE"'; } ?><?php if( TRUE == Settings::getPushEmptyData() ) { echo 'checked'; } ?>>Send no data, just get the result<br>
<input type="radio" name="expectedResult" value="FALSE" <?php if( FALSE == Settings::getExpectedResult() ) { echo 'checked'; } ?>>Send failure(red) upon request<br>
<input type="radio" name="expectedResult" value="TRUE" <?php if( TRUE == Settings::getExpectedResult() ) { echo 'checked'; } ?>>Send success(green) upon request<br>
<input type="submit" value="Set">
</div>
</form>



<div>Audio shots:</div>
<form name="shots" action=<?php echo $resultFormUrl; ?>  method="POST">
<div>
<input type="hidden" name="action" value="removeAudioShots"/>
<?php getAudioShots();  ?>
<input type="submit" value="Remove selections">
</div>
</form>


</body>
</html>
