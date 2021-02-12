#my_test.sh

#Test the first set of file ----------------------------------------------
echo java - jar ../EncrypterDecrypter.jar e sample1.txt sample1.enc '"This is the key"'
echo | java -jar ../EncrypterDecrypter.jar e sample1.txt sample1.enc "This is the key"


echo java -jar ../EncrypterDecrypter.jar d sample1.enc sample1.dec '"This is the key"' 
echo | java -jar ../EncrypterDecrypter.jar d sample1.enc sample1.dec "This is the key"

check1=0
if diff sample1.txt sample1.dec > /dev/null
then
    check1=1
fi


#Test the second set of files ----------------------------------------------
echo java -jar ../EncrypterDecrypter.jar e sample2.txt sample2.enc '"This is the key"'
echo | java -jar ../EncrypterDecrypter.jar e sample2.txt sample2.enc "This is the key"

echo java -jar ../EncrypterDecrypter.jar d sample2.enc sample2.dec '"This is the key"'
echo | java -jar ../EncrypterDecrypter.jar d sample2.enc sample2.dec "This is the key"

check2=0
if diff sample2.txt sample2.dec > /dev/null
then
    check2=1
fi

#Test the third set of files----------------------------------------------
echo java -jar ../EncrypterDecrypter.jar e sample3.txt sample3.enc '"This is the key"'
echo | java -jar ../EncrypterDecrypter.jar e sample3.txt sample3.enc "This is the key"

echo java -jar ../EncrypterDecrypter.jar d sample3.enc sample3.dec '"This is the key"'
echo | java -jar ../EncrypterDecrypter.jar d sample3.enc sample3.dec "This is the key"

check3=0
if diff sample3.txt sample3.dec > /dev/null
then
    check3=1
fi

#Test the fourth set of files ----------------------------------------------
echo java -jar ../EncrypterDecrypter.jar e sample4.txt sample4.enc '"This is the key"'
echo | java -jar ../EncrypterDecrypter.jar e sample4.txt sample4.enc "This is the key"

echo java -jar ../EncrypterDecrypter.jar d sample4.enc sample4.dec '"This is the key"'
echo | java -jar ../EncrypterDecrypter.jar d sample4.enc sample4.dec "This is the key"

check4=0
if diff sample4.txt sample4.dec > /dev/null
then
    check4=1
fi


#Check the resutl =========================================================
echo "\n\n\n=============================================================================\n"
echo "Here are the result of tests:" 

declare -i diffFilesCounter=0

if [ $check1 = 0 ];
then
    echo "\n---------------------sample1.txt and sample1.dec---------------------\n"
    echo diff sample1.txt sample1.dec 
    diff sample1.txt sample1.dec 
    let "diffFilesCounter += 1"
fi

if [ $check2 = 0 ];
then
    echo "\n---------------------sample2.txt and sample2.dec---------------------\n"
    echo diff sample2.txt sample2.dec
    diff sample2.txt sample2.dec 
    let "diffFilesCounter += 1"
fi

if [ $check3 = 0 ];
then
    echo "\n---------------------sample3.txt and sample3.dec---------------------\n"
    echo jar diff sample3.txt sample3.dec
    diff sample3.txt sample3.dec 
    let "diffFilesCounter += 1"
fi

if [ $check4 = 0 ];
then
    echo "\n---------------------sample4.txt and sample4.dec---------------------\n"
    echo diff sample4.txt sample4.dec
    diff sample4.txt sample4.dec
    let "diffFilesCounter += 1"
fi


if [ $diffFilesCounter = 0 ];
then
    echo "All the files were encrypted and decrypted successfully."
fi

echo "\n============================================================================="
echo "\n\n\n"

read -p "Press enter to exit test..."