## I Can Win

When performing the task, you should use Selenium WebDriver, testing framework and Page Object. You should automate the following scenario:

1. Open https://pastebin.com or similar service in any browser
2. Create New Paste with next details:
   * Code: "Hello from WebDriver"
   * Paste Expiration: "10 Minutes"
   * Paste Name / Title: "helloweb"
3. Save the paste and check that page title matches Paste Name / Title.

## Bring It On

When performing the task, you should use Selenium WebDriver, testing framework and Page Object. You should automate the following scenario:

1. Open https://pastebin.com or similar service in any browser
2. Create New Paste with next details:
   * Code:
````
git config --global user.name  "New Sheriff in Town"
git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
git push origin master --force
````
   * Syntax Highlighting: "Bash"
   * Paste Expiration: "10 Minutes"
   * Paste Name / Title: "how to gain dominance among developers"
3. Save the paste and check following:
   * Browser title matches Paste Name / Title
   * Syntax highlighting for bash
   * Check that the code matches the one entered in 2.




## Hurt me plenty:

When performing the task, you should use Selenium WebDriver, testing framework and Page Object. You should automate the following scenario:

1. Open https://cloud.google.com/
2. Press Search button at the top of the page, enter the search box "Google Cloud Platform Pricing Calculator"
3. Start search by pressing search button.
4. Click on result "Google Cloud Platform Pricing Calculator" in search results and go to Calculator page.
5. Activate section COMPUTE ENGINE at the top of the page
6. Fill the Calculator form with following information:
    * Number of instances: 4
    * What are these instances for?: leave blank
    * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
    * VM Class: Regular
    * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
    * Select Add GPUs
        * Number of GPUs: 1
        * GPU type: NVIDIA Tesla V100
    * Local SSD: 2x375 Gb
    * Datacenter location: Frankfurt (europe-west3)
    * Committed usage: 1 Year
7. Press Add to Estimate
8. Check data correspondence in following fields: VM Class, Instance type, Region, local SSD, commitment term
9. Check that monthly rental amount matching the amount received when passing test manually. 

### Build only with command prompt: 
``mvn -Dbrowser=chrome -Denvironment=staging clean test``


## Hardcore:

When performing the task, you should use Selenium WebDriver, testing framework and Page Object. You should automate the following scenario:

1. Open https://cloud.google.com/
2. Press Search button at the top of the page, enter the search box "Google Cloud Platform Pricing Calculator"
3. Start search by pressing search button.
4. Click on result "Google Cloud Platform Pricing Calculator" in search results and go to Calculator page.
5. Activate section COMPUTE ENGINE at the top of the page
6. Fill the Calculator form with following information:
   * Number of instances: 4
   * What are these instances for?: leave blank
   * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
   * VM Class: Regular
   * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
   * Select Add GPUs
      * Number of GPUs: 1
      * GPU type: NVIDIA Tesla V100
   * Local SSD: 2x375 Gb
   * Datacenter location: Frankfurt (europe-west3)
   * Committed usage: 1 Year
7. Press Add to Estimate
8. Pick EMAIL ESTIMATE
9. Open in new tab https://yopmail.com/ or similar service for temporal emails generation
10. Copy the email address generated in yopmail.com
11. Return to Calculator, enter the email address in corresponding field
12. Press SEND EMAIL
13. Wait for email with cost calculation and check that Total Estimated Monthly Cost in email matches the one in Calculator.