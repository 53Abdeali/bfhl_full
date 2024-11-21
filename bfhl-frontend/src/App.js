import React, { useState } from "react";
import axios from "axios";
import Select from "react-select";

const App = () => {
  const [jsonInput, setJsonInput] = useState("");
  const [response, setResponse] = useState(null);
  const [error, setError] = useState("");
  const [selectedOptions, setSelectedOptions] = useState([]);

  const options = [
    { value: "alphabets", label: "Alphabets" },
    { value: "numbers", label: "Numbers" },
    { value: "highest_lowercase_alphabet", label: "Highest Lowercase Alphabet" },
  ];

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const parsedInput = JSON.parse(jsonInput);
      if (!parsedInput.data) {
        throw new Error("JSON must include a 'data' key.");
      }
      const res = await axios.post("http://localhost:8080/bfhl", parsedInput); // Update with your backend URL
      setResponse(res.data);
      setError("");
    } catch (err) {
      setError("Invalid JSON format or missing 'data' key.");
    }
  };

  const renderResponse = () => {
    if (!response) return null;
    const filteredResponse = selectedOptions.reduce((acc, option) => {
      acc[option.value] = response[option.value];
      return acc;
    }, {});
    return (
      <div>
        <h3>Response:</h3>
        <pre>{JSON.stringify(filteredResponse, null, 2)}</pre>
      </div>
    );
  };

  return (
    <div style={{ margin: "20px" }}>
      <h1 style={{ textAlign: "center" }}>BFHL Challenge</h1>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "10px" }}>
          <label>JSON Input:</label>
          <textarea
            value={jsonInput}
            onChange={(e) => setJsonInput(e.target.value)}
            rows="5"
            style={{ width: "100%", padding: "10px" }}
            placeholder='Example: {"data": ["1", "a", "b"]}'
          />
        </div>
        <button type="submit" style={{ padding: "10px 20px", cursor: "pointer" }}>
          Submit
        </button>
      </form>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <div style={{ marginTop: "20px" }}>
        <label>Select Fields to Display:</label>
        <Select
          isMulti
          options={options}
          onChange={setSelectedOptions}
          value={selectedOptions}
        />
      </div>
      {renderResponse()}
    </div>
  );
};

export default App;
