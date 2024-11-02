console.log("script loaded");

let currenTheme = getTheme();
changeTheme();

// todo theme
function changeTheme() {
    // set to webpage
    document.querySelector('html').classList.add(currenTheme);

    // set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");
    changeThemeButton.addEventListener("click", ()=>{
        console.log("Button clicked");
        const oldTheme = currenTheme;

        if(currenTheme === "dark"){
            currenTheme="light";
        }else{
            currenTheme="dark";
        }

        // update in local storage first the changed theme for next time
        setTheme(currenTheme);
        //remove old theme
        document.querySelector('html').classList.remove(oldTheme);  
        //add new theme
        document.querySelector('html').classList.add(currenTheme);

    });
}

//Set theme to local storage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// Get theme from local storage
function getTheme() {
    const storedTheme = localStorage.getItem("theme");
    return storedTheme ? storedTheme : "light";
}
