import React from 'react';

const Photo: React.FC = () => {
  return (
    <div>
        <form onSubmit={handleSubmit}>
        <input type="file" onChange={handleImageChange} />
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default Photo;