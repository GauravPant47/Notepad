// Create style element
const style = document.createElement('style');
document.head.appendChild(style);

// Define CSS rules
const cssRules = `
    .btn {
        margin: 10px;
        height: 35px;
        width: 120px;
        align-items: center;
        justify-content: center;
        display: flex;
        cursor: pointer;
        border: 1.3px solid #000;
    }

    .btn-1 {
        border: 2px solid #17A9FD;
        background-color: #17A9FD;
        color: #fff;
        font-size: 15px;
        font-weight: 550;
        border-radius: 50px;
        font-family: 'Poppins', sans-serif;
        transition: 2s;
        z-index: 1;
    }

    .btn:hover {
        height: 37px;
        width: 122px;
    }

    .btn-1:hover {
        border: 1.5px solid #17A9FD;
        background-color: #fff;
        color: #000;
    }

    .btn-2 {
        border: 2px solid #64d453;
        background-color: #64d453;
        color: #fff;
        font-size: 15px;
        font-weight: 550;
        border-radius: 50px;
        font-family: 'Roboto', sans-serif;
        transition: 2s;
        z-index: 1;
    }

    .btn-2:hover {
        border: 1.5px solid #64d453;
        background-color: #fff;
        color: #000;
    }

    .btn-3 {
        border: 2px solid #f74914;
        background-color: #f74914;
        color: #fff;
        font-size: 15px;
        font-weight: 550;
        border-radius: 50px;
        font-family: 'Lato', sans-serif;
        transition: 2s;
        z-index: 1;
    }

    .btn-3:hover {
        border: 1.5px solid #f74914;
        background-color: #fff;
        color: #000;
    }

    .btn-4 {
        border: 2px solid rgb(228, 96, 96);
        background: linear-gradient(to left, rgb(228, 125, 125), rgb(149, 149, 217));
        color: #fff;
        font-size: 15px;
        font-weight: 550;
        border-radius: 50px;
        font-family: 'Noto Sans', sans-serif;
        transition: 2s;
        z-index: 1;
    }

    .btn-4:hover {
        border: 1.5px solid rgb(228, 96, 96);
        color: #000;
    }

    .btn-5 {
        border: 2px solid #4f74e3;
        background: radial-gradient(ellipse at top, #17A9FD, #4f74e3);
        color: #fff;
        font-size: 15px;
        font-weight: 550;
        font-size: 20px;
        border-radius: 50px;
        font-family: 'Edu SA Beginner', cursive;
        transition: 2s;
        z-index: 1;
    }

    .btn-5:hover {
        border: 1.5px solid #4f74e3;
        background: #fff;
        color: #000;
    }
`;

// Append CSS rules to the style element
style.appendChild(document.createTextNode(cssRules));
