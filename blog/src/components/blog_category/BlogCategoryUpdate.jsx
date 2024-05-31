import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import BlogCategoryApi from '../../services/BlogCategoryApi';

function BlogCategoryUpdate({ t }) {
  const navigate = useNavigate();
  const { id } = useParams();

  const [categoryName, setCategoryName] = useState('');
  const [error, setError] = useState(null);
  const [spinner, setSpinner] = useState(false);
  const [multipleRequest, setMultipleRequest] = useState(false);

  useEffect(() => {
    localStorage.setItem("blog_category_update_id", id);
    BlogCategoryApi.categoryApiFindById(id)
      .then((response) => {
        if (response.status === 200) {
          setCategoryName(response.data.categoryName);
        }
      })
      .catch((err) => {
        console.error(err);
      });
  }, [id]);

  const clear = () => {
    setCategoryName('');
  };

  const categoryNameOnChange = (event) => {
    const { value } = event.target;
    setCategoryName(value);
  };

  const onSubmitForm = (event) => {
    event.preventDefault();
  };

  const blogCategoryUpdateSubmit = async (event) => {
    event.preventDefault();

    const blogCategoryObject = {
      categoryName
    };

    setError(null);
    setSpinner(true);
    setMultipleRequest(true);

    try {
      const response = await BlogCategoryApi.categoryApiUpdate(id, blogCategoryObject);
      if (response.status === 200) {
        setSpinner(false);
        setMultipleRequest(false);
        navigate("/blog/category/list");
      }
    } catch (err) {
      console.error(err);
      setError(err.response.data.validationErrors);
      setSpinner(false);
      setMultipleRequest(false);
    }
  };

  const spinnerData = () => {
    if (spinner) {
      return (
        <div className="spinner-border text-warning" role="status">
          <span className="sr-only">Loading...</span>
        </div>
      );
    } else {
      return null;
    }
  };

  const inputInvalidErrorClass = error ? "form-control is-invalid" : "form-control";

  return (
    <div className="container mt-5">
      <h1>Blog Category Update</h1>
      <form onSubmit={onSubmitForm}>
        <div className="form-group">
          <label htmlFor="categoryName">Category Name</label>
          <input 
            type="text" 
            className={inputInvalidErrorClass} 
            id="categoryName" 
            name="categoryName"
            autoFocus={true} 
            placeholder="Enter Category Name" 
            required={true}
            onChange={categoryNameOnChange} 
            value={categoryName}
          />
          {error && <div className="invalid-feedback">{error.categoryName}</div>}
        </div>
        <button 
          type="reset"
          onClick={clear}
          className="btn btn-danger mt-2 shadow" 
        >
          Temizle
        </button>
        <button 
          type="submit"
          onClick={blogCategoryUpdateSubmit}
          className="btn btn-primary mt-2 ms-2 shadow"
          disabled={multipleRequest}
        >
          {spinnerData()}
          Update
        </button>
      </form>
    </div>
  );
}

export default BlogCategoryUpdate;