<?php

class Settings {

    public static function getExpectedResult() {
        return file_exists( "./settings/expectedresultyes" );
    }

    public static function setExpectedResult($result) {
        if( $result == 'TRUE') 
        {
            $myfile = fopen("./settings/expectedresultyes", "w") or die("Unable to create file!");
            fclose( $myfile );
        } else {
            unlink( "./settings/expectedresultyes" ); 
        }
    }

    public static function getPushEmptyData() {
        return file_exists( "./settings/pushemptydatayes" );
    }

    public static function setPushEmptyData($result) {
        if (isset($result))
        {
            $myfile = fopen("./settings/pushemptydatayes", "w") or die("Unable to create file!");
            fclose( $myfile );
        } else {
            unlink( "./settings/pushemptydatayes" ); 
        }
    }

}

?>


