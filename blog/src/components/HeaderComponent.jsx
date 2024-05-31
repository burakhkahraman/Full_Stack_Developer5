import React from 'react'
import { withTranslation } from 'react-i18next'
import { Link } from "react-router-dom";
import DarkMode from "./DarkMode/DarkMode";
import OtherLanguageReusability from '../internationalization/OtherLanguageReusability';

function HeaderComponent(props) {
  const { t } = props;

  return (
    <>
      <header>
        <nav className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
          <div className="container">
            {/* Absolute Path */}
            <Link className="navbar-brand" style={{ color: `#123}` }} to="/">
              <i className={props.logo}></i>
            </Link>

            <button
              className="navbar-toggler d-lg-none"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#collapsibleNavId"
              aria-controls="collapsibleNavId"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon" />
            </button>
            <div className="collapse navbar-collapse" id="collapsibleNavId">
              <ul className="navbar-nav me-auto mt-2 mt-lg-0">
                <li className="nav-item">
                  {/* Root: relative Path */}
                  <Link className="nav-link active" to="/"><i className="fa-solid fa-house-chimney"></i> {t('home')} </Link>
                </li>
              </ul>

              {/* Register / Login */}
              <ul className="navbar-nav ms-auto mt-2 mt-lg-0">
                {/* i18n Language */}
                <li className="nav-item dropdown">
                  <button
                    className="nav-link dropdown-toggle btn btn-link"
                    id="dropdownId"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    {t('Role')}
                  </button>
                  <div className="dropdown-menu" aria-labelledby="dropdownId">
                    <Link className="dropdown-item" to="/role/list">{t('role_list')}</Link>
                    <Link className="dropdown-item" to="/role/create">{t('role_create')}</Link>
                  </div>
                </li>

                <li className="nav-item dropdown">
                  <button
                    className="nav-link dropdown-toggle btn btn-link"
                    id="dropdownId"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    {t('Registers')}
                  </button>
                  <div className="dropdown-menu" aria-labelledby="dropdownId">
                    <Link className="dropdown-item" to="/register/list">{t('register_list')}</Link>
                    <Link className="dropdown-item" to="/register/create">{t('register_create')}</Link>
                  </div>
                </li>

                {/* Search Form */}
                <form className="d-flex my-2 my-lg-0">
                  <input
                    type="text"
                    id="tags"
                    className="form-control me-sm-2"
                    placeholder={t('search')}
                  />
                  <button
                    type="submit"
                    className="btn btn-outline-success my-2 my-sm-0"
                  >
                    {t('Search')}
                  </button>
                </form>
              </ul>

              {/* Dark Mode */}
              <ul className="navbar-nav me-auto mt-2 mt-lg-0">
                <li className="nav-item">
                  <DarkMode />
                </li>

                <li className="nav-item dropdown">
                  <button
                    className="nav-link dropdown-toggle btn btn-link"
                    id="dropdownId"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    {t('Language')}
                  </button>
                  <div className="dropdown-menu" aria-labelledby="dropdownId">
                    <OtherLanguageReusability />
                  </div>
                </li>

                <li className="nav-item dropdown">
                  <button
                    className="nav-link dropdown-toggle btn btn-link"
                    id="dropdownId"
                    data-bs-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    {t('Login')}
                  </button>
                  <div className="dropdown-menu" aria-labelledby="dropdownId">
                    <Link className="dropdown-item" to="/login">{t('login')}</Link>
                    <Link className="dropdown-item" to="/register/create">{t('register')}</Link>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <span style={{ marginBottom: "2rem" }}>.</span>
      </header>
    </>
  )
}

// EXPORT
export default withTranslation()(HeaderComponent);
