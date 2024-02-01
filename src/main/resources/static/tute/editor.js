// script.js

// Function to show the table dropdown
function toggleDropdown() {
    var dropdown = document.getElementById("tableDropdown");
    dropdown.style.display = (dropdown.style.display === "block") ? "none" : "block";
}

function createTable() {
    var rows = document.getElementById("rowsInput").value;
    var columns = document.getElementById("columnsInput").value;

    if (rows && columns) {
        var table = '<table>';
        for (var i = 0; i < rows; i++) {
            table += '<tr>';
            for (var j = 0; j < columns; j++) {
                table += '<td contenteditable="true"></td>';
            }
            table += '</tr>';
        }
        table += '</table>';

        // Insert the table into the editor
        var editor = document.getElementById("editor");
        editor.innerHTML += table;

        // Hide the dropdown after creating the table
        document.getElementById("tableDropdown").style.display = "none";
    } else {
        alert("Please enter valid values for rows and columns.");
    }
}

// Function to change font
function changeFont(font) {
    document.execCommand("fontName", false, font);
}

// Function to change font size
function changeFontSize(fontSize) {
    // Implement font size change functionality here
    document.execCommand("fontSize", false, fontSize);
}

// Function to toggle bullets
function toggleBullets() {
    // Implement toggle bullets functionality here
    document.execCommand("insertUnorderedList", false, null);
}

// Function to toggle numbering
function toggleNumbering() {
    // Implement toggle numbering functionality here
    document.execCommand("insertOrderedList", false, null);
}

// Function to show text color dropdown
function showTextColorDropdown() {
    var dropdown = document.getElementById("textColorDropdown");
    dropdown.style.display = (dropdown.style.display === "block") ? "none" : "block";
}

function changeTextColor(color) {
    document.execCommand("foreColor", false, color);
    var dropdown = document.getElementById("textColorDropdown");
    dropdown.style.display = "none";
}



// Function to show text highlight dropdown
function showTextHighlightDropdown() {
    var dropdown = document.getElementById("highlightDropdown");
    dropdown.style.display = (dropdown.style.display === "block") ? "none" : "block";
}

function highlightSelectedText(color) {
    document.execCommand("hiliteColor", false, color);
    document.getElementById("highlightDropdown").style.display = "none";
}

function removeTextHighlight() {
    document.execCommand("removeFormat", false, null);
    document.getElementById("highlightDropdown").style.display = "none";
}
// Function to align text
function alignText(align) {
    // Implement text alignment functionality here
    document.execCommand("justify" + align, false, null);
}

// Function to clear all formats
function clearFormats() {
    // Implement clear formats functionality here
    document.execCommand("removeFormat", false, null);
}

// Function to apply styles (bold, italic, underline)
function applyStyle(style) {
    // Implement style application functionality here
    document.execCommand(style, false, null);
}

// Function to undo
function undo() {
    // Implement undo functionality here
    document.execCommand("undo", false, null);
}

// Function to add a link
// function addLink() {
//     // Implement add link functionality here
//     var url = prompt("Enter the URL:");
//     if (url) {
//         document.execCommand("createLink", false, url);
//     }
// }

// Function to add a picture
// function addPicture() {
//     // Implement add picture functionality here
//     var url = prompt("Enter the image URL:");
//     if (url) {
//         document.execCommand("insertImage", false, url);
//     }
// }

// Function for format painter

var formatPainterStyle = {};
var formatPainterEnabled = true;

function formatPainter() {
    if (formatPainterEnabled) {
        var selection = window.getSelection();
        if (selection.rangeCount > 0) {
            var range = selection.getRangeAt(0);
            formatPainterStyle.font = document.queryCommandValue('fontName');
            formatPainterStyle.fontSize = document.queryCommandValue('fontSize');
            console.log('Format Painter enabled.');
        }
        formatPainterEnabled = false;
    }
}

function applyFormatPainter() {
    if (formatPainterStyle.font) {
        document.execCommand("fontName", false, formatPainterStyle.font);
    }
    if (formatPainterStyle.fontSize) {
        document.execCommand("fontSize", false, formatPainterStyle.fontSize);
    }
    console.log('Format Painter applied.');
}

document.getElementById("editor").addEventListener("mouseup", function () {
    applyFormatPainter();
});


function writeBelowTable() {
    var editor = document.getElementById('editor');
    var lastChild = editor.lastChild;

    // Check if the last child is a table
    if (lastChild && lastChild.nodeName.toLowerCase() === 'table') {
        // Insert a new paragraph after the table
        var newParagraph = document.createElement('p');
        newParagraph.textContent = '';
        editor.appendChild(newParagraph);
        saveState();
    }
}