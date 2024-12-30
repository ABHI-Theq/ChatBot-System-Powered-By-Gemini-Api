// Helper to store history in localStorage

const search=document.getElementById("search")


function saveHistory(prompt, response) {
    let history = JSON.parse(localStorage.getItem('history')) || [];
    history.push({ prompt, response });
    localStorage.setItem('history', JSON.stringify(history));
}

// Handle form validation and prompt submission on the first page
if(document.getElementById('promptForm')!=null){
document.getElementById('promptForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const promptInput = document.getElementById('promptInput').value.trim();
    if (!promptInput) {
        alert('Please enter a prompt');
        return;
    }
    try {
    search.innerText="Generating..."
        // Send the prompt input to the API and get the response
        const response = await fetch("http://localhost:8080/api/ask", {
            method: 'POST', // Use POST method
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ question: promptInput }), // Send the prompt as question
        });

        // Check if the response is ok
        if (!response.ok) {
            throw new Error('Failed to fetch response from API');
        }

        // Extract the response text
        const apiResponse = await response.text(); // Since the backend returns a string

        // Display the response on the first page
        document.getElementById('responseContainer').innerText = apiResponse;

        search.innerText="search"
        // Save to history
        saveHistory(promptInput, apiResponse);
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred while fetching the response. Please try again later.');
    }
});
}


// Fetch all previous prompts and responses from /api/history and display them
async function loadHistory() {
    try {
        const response = await fetch("http://localhost:8080/api/history");
        if (!response.ok) {
            throw new Error('Failed to fetch chat history');
        }

        const historyData = await response.json();

        const historyContainer = document.getElementById('historyContainer');

        // If history is empty, show no records message
        if (historyData.length === 0) {
            historyContainer.innerHTML = '<p>No previous records available.</p>';
            return;
        }

        // Display each history item
        historyData.forEach(item => {
            const historyItem = document.createElement('div');
            historyItem.classList.add('historyItem');
            historyItem.innerHTML = `
                <p><strong>Prompt:</strong> ${item.prompt}</p>
                <p><strong>Response:</strong> ${item.response}</p>
                <p><strong>Created At:</strong> ${item.createdAt}</p> <!-- Assuming createdAt is part of the response -->
            `;
            historyContainer.appendChild(historyItem);
        });
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('historyContainer').innerHTML = '<p>An error occurred while fetching history. Please try again later.</p>';
    }
}

// Load history when on the history page
if (window.location.pathname.includes('history')) {
    loadHistory();
}
