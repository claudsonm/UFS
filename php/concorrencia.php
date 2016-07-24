<?php
declare(ticks=1);
$pid = pcntl_fork();
if ($pid == -1) die("could not fork"); 
else if ($pid) exit(); // we are the parent 
else { /* we are the child*/ }

// detatch from the controlling terminal
if (posix_setsid() == -1) die("could not detach from terminal");

// setup signal handlers
pcntl_signal(SIGTERM, "sig_handler");
pcntl_signal(SIGHUP, "sig_handler");

while (1) { /* loop forever performing tasks do something interesting here*/ }

function sig_handler($signo) {
     switch ($signo) {
         case SIGTERM:
             // handle shutdown tasks
             exit;
             break;
         case SIGHUP:
             // handle restart tasks
             break;
         default:
             // handle all other signals
     }
}
?>