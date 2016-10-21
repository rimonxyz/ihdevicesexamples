#!/bin/bash

# The script for iHealth SDK Output

set -e

SDK_ROOT_DIR="/Users/jing/Desktop/App-Android/Android_SDK"
PROGUARD_DIR="/Users/jing/Library/Android/sdk/tools/proguard/lib/proguard.jar"
SDK_OUTPUT_DIR="output"

cd $SDK_ROOT_DIR

if [ -e "$SDK_OUTPUT_DIR" ]; then
	rm -rf $SDK_OUTPUT_DIR
fi

mkdir $SDK_OUTPUT_DIR
cd $SDK_OUTPUT_DIR
mkdir jar

echo "/*****************************************/"
echo "/**                                     **/"
echo "/**     create a android project        **/"
echo "/**                                     **/"
echo "/*****************************************/"

cp -Rp $SDK_ROOT_DIR/ihealthdevicesdk $SDK_ROOT_DIR/output/
rm -rfv $SDK_ROOT_DIR/output/iHealthSDKLib/ihealthdevicesdk/
rm -rfv $SDK_ROOT_DIR/output/iHealthSDKLib/output/
rm -rfv $SDK_ROOT_DIR/output/iHealthSDKLib/tools/

echo "/*****************************************/"
echo "/**                                     **/"
echo "/**     generate and proguard jar       **/"
echo "/**                                     **/"
echo "/*****************************************/"

cd ../tools
command ant
java -jar  $PROGUARD_DIR @$SDK_ROOT_DIR/tools/ihealthlib.pro -dontwarn
cp $SDK_ROOT_DIR/ihealthdevicesdk/libs/btle.jar  $SDK_ROOT_DIR/output/iHealthSDKLib/app/libs/
cp $SDK_ROOT_DIR/ihealthdevicesdk/libs/d2xx.jar  $SDK_ROOT_DIR/output/iHealthSDKLib/app/libs/
cp $SDK_ROOT_DIR/ihealthdevicesdk/libs/pl2303driver.jar  $SDK_ROOT_DIR/output/iHealthSDKLib/app/libs/
cp $SDK_ROOT_DIR/ihealthdevicesdk/libs/smartlinklib3.6.4_product.jar  $SDK_ROOT_DIR/output/iHealthSDKLib/app/libs/
cp $SDK_ROOT_DIR/output/jar/iHealthDeviceLib.jar  $SDK_ROOT_DIR/output/iHealthSDKLib/app/libs/
