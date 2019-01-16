#!/bin/bash
for filename in ./in/*.REM
do
  echo "$(basename "$filename")"
done
