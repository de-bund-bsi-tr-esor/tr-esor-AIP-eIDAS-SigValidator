# Third-party test data

The following files in this directory are JAdES test vectors taken unmodified from the
[esig-dss](https://github.com/esig/dss) project (module `dss-jades`, version 6.5,
`dss-jades/src/test/resources/`), used here to exercise `JAdESChecker`'s structural detection:

- altered-jws.json
- jades-b-level-with-etsiu-in-crit.json
- jades-detached-by-uri-encoded-pars.json
- jades-detached-by-uri-hash-encoded-pars.json
- jades-flattened-BpB-detached-objectByURIHash.json
- jades-level-b-full-type.json
- jades-t-clear-etsiu.json
- jades-t-level-with-etsiu-in-crit.json
- jades-with-counter-signature.json
- jades-with-crit-with-wrong-entry-type.json
- jades-wrong-etsiu-type.json
- jades-wrong-x5c-header.json
- jws-serialization-no-signatures.json
- malformed-jades-serialization.json
- serialization-extra-element.json
- simple-detached-wrong-algo.json
- simple-detached.json

esig-dss is licensed under the GNU Lesser General Public License, Version 2.1 (LGPL-2.1); see
`licenses/LGPL-2.1.txt` at the repository root. Used here as a dependency only (no source code
vendored) plus these unmodified test fixtures.

All other files in this directory (`JadesWithCertificateReferenceButNoEtsiHeader.json`,
`JadesWithEmptyEtsiUIs.json`, `JadesWithEmptyProtected.json`,
`jades-with-EtsiHeader-but-NoCertificate.json`) are self-authored for this project.
