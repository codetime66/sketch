#!/bin/bash
exec 2> /dev/null
if [ -z "$(ls -A ./in/*.REM)" ]; then
   echo "Empty"
else
   echo "Not Empty"
fi
