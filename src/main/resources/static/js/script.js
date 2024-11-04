console.log("script loaded");

let currenTheme = getTheme();

document.addEventListener('DOMContentLoaded',()=>{
    changeTheme();
});


// todo theme
function changeTheme() {
    // set to webpage
    changePageTheme(currenTheme, currenTheme);

    // set the listener to change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");

    // Initial button text
    changeThemeButton.querySelector('span').textContent = currenTheme === "light" ? "Dark" : "Light";

    changeThemeButton.addEventListener("click", () => {
        console.log("Button clicked");
        const oldTheme = currenTheme;

        // Toggle the theme
        currenTheme = currenTheme === "dark" ? "light" : "dark";

        changePageTheme(currenTheme, oldTheme);

    });
}

// change current page theme
function changePageTheme(theme, oldTheme){
    
    // Update in local storage
    setTheme(currenTheme);
        
    // Remove old theme and add new theme
    document.querySelector('html').classList.remove(oldTheme);  
    document.querySelector('html').classList.add(currenTheme);
    
    // Update button text after theme change
    document.querySelector('#theme_change_button')
    .querySelector('span').textContent = theme === "light" ? "Dark" : "Light";
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
