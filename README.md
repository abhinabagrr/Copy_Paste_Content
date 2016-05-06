# Copy_Paste_Content (windows)
  Copy and paste content of file w/o opening the file. 
  I faced issue while resolving product bugs, when qe gives us testdata file and we read the testdata and execute our junit. But as usual they always give data file in defferent names. Hence we needed to change the code/properties to point to new file. To solve this issue I came up with this tool, which will copy the content of a testdata file to existing file and we can execute our tests w/o any code modification.

The project contains two zip files. One that holds the executable i.e. (https://github.com/abhinabagrr/Copy_Paste_Content/blob/master/cpc_1.0.zip?raw=true)

And second one is the eclipse source (https://github.com/abhinabagrr/Copy_Paste_Content/blob/master/cpc_1.0_eclipse.zip)

The aim of this tool is to copy the content of a file, w/o opening it. Just right click on the file say "Copy Content".
Now right click on another file say "Paste Content". The first file contents would be copied to the second file.

#Installation steps
  1. Download cpc_1.0.zip, extract to your local directory
  2. Run installer.exe.
  3. Window will ask your permission as it is going to update in registry, say YES.
  4. If you have any confusion over what we are adding to registry, you can run it in manual mode too.

#Manual mode installation
  1. run command installer.exe -m.
  2. Once execution finishes, you can see two .reg file, cp.reg and pst.reg.
  3. Import these two files to registry and it is done.

#Usage
  1. Once installed, right click on any file and say "Copy Content".
  2. Now right click on destination file and say "Paste Content"
  3. If source and destination files are not of same type, it asks for your permission to overwrite.

#Eclipse project
  1. Download cpc_1.0_eclipse.zip and import as a project in eclipse.
  2. Executable batch files are available in resources folder.
  3. Source codes are available in src folder.
  4. You change the source code and export cpc.jar under executable_folder\lib (i.e. cpc_1.0\lib).
  
