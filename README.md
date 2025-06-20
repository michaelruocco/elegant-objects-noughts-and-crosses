# Library Template

[![Build](https://github.com/michaelruocco/elegant-objects-noughts-and-crosses/workflows/pipeline/badge.svg)](https://github.com/michaelruocco/elegant-objects-noughts-and-crosses/actions)
[![codecov](https://codecov.io/gh/michaelruocco/elegant-objects-noughts-and-crosses/branch/main/graph/badge.svg?token=FWDNP534O7)](https://codecov.io/gh/michaelruocco/elegant-objects-noughts-and-crosses)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/272889cf707b4dcb90bf451392530794)](https://www.codacy.com/gh/michaelruocco/elegant-objects-noughts-and-crosses/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/elegant-objects-noughts-and-crosses&amp;utm_campaign=Badge_Grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_elegant-objects-noughts-and-crosses&metric=alert_status)](https://sonarcloud.io/dashboard?id=michaelruocco_elegant-objects-noughts-and-crosses)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_elegant-objects-noughts-and-crosses&metric=sqale_index)](https://sonarcloud.io/dashboard?id=michaelruocco_elegant-objects-noughts-and-crosses)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_elegant-objects-noughts-and-crosses&metric=coverage)](https://sonarcloud.io/dashboard?id=michaelruocco_elegant-objects-noughts-and-crosses)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_elegant-objects-noughts-and-crosses&metric=ncloc)](https://sonarcloud.io/dashboard?id=michaelruocco_elegant-objects-noughts-and-crosses)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.michaelruocco/elegant-objects-noughts-and-crosses.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.michaelruocco%22%20AND%20a:%22elegant-objects-noughts-and-crosses%22)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Overview

This repo contains an attempt at a simple noughts and crosses (or tic-tac-toe)
game written in Java, whilst attempting to stick to the principals outlined in
the book [Elegant Objects](https://www.amazon.co.uk/Elegant-Objects-1-Yegor-Bugayenko/dp/1519166915).

## Useful Commands

```gradle
// cleans build directories
// checks dependency versions
// checks for gradle issues
// formats code
// builds code
// runs tests
// checks dependencies for vulnerabilities
./gradlew clean dependencyUpdates lintGradle spotlessApply build
```