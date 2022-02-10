<?php
    //echo phpinfo();    
    //error_reporting(E_ALL);
    error_reporting(E_ERROR | E_WARNING | E_PARSE);
    ini_set('display_errors', 'on');

include_once "settings.php";

if( Settings::getPushEmptyData() ) {
    echo 'TRUE';
} else {
    echo 'FALSE';
}

?>

