import React, { ChangeEvent, useState } from 'react';
import axios from 'axios';

const Photo: React.FC = () => {
  interface BookInfo {
    title: string;
    author: string;
    libraryLocation: string;
   
  }

  const [image, setImage] = useState<File | null>(null);
  const [bookInfo, setBookInfo] = useState<BookInfo | null>(null);

  const handleImageChange = (event: ChangeEvent<HTMLInputElement>) => {
    const file = (event.target.files?.[0]);
    if(file) {
      setImage(file);
    }
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const formData = new FormData();
    if (image !== null) {
      formData.append("file", image);
  }
 

    try {
      const response = await axios.post('http://localhost:8080/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      setBookInfo(response.data); // 백엔드에서 받은 책 정보 설정
    } catch (error) {
      console.error('Error uploading the image:', error);
    }
  };



  return (
    <div>
        <form onSubmit={handleSubmit}>
        <input type="file" onChange={handleImageChange} />
        <button type="submit">Submit</button>
      </form>
      {bookInfo && (
        <div>
          <h3>Book Information</h3>
          <p>Title: {bookInfo.title}</p>
          <p>Author: {bookInfo.author}</p>
          <p>Library Location: {bookInfo.libraryLocation}</p>
        </div>
      )}
    </div>
  );
};

export default Photo;