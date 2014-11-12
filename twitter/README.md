Sample GWT App for DevFest NE 2014 / Aracaju-Brazil
====================

A simple twitter application made upon some of the best features of GWT.

Features
-------------

* Activities & Places
* MVP Design
* Events and EventBus
* History Management
* UiBinder
* UiRenderer
* CellWidgets (CellList)
* ClientBundle, CssResource with auto generation
* JSNI (native javascript manipulation)
* Bootstrap & jQuery integration
* RPC with asynchronous interface auto generation
* SuperDevMode

How to use
----------

### Use SuperDevMode

Enter into the project directory:

1. `mvn clean install -Dgwt.draftCompile`
2. In one terminal window: `cd *-client && mvn gwt:run-codeserver -Ddev`
3. In another terminal window: `cd *-server && mvn jetty:start -Ddev`

The `-Dgwt.draftCompile` in the first step is not required, it's only to speed up the
GWT compilation by disabling optimizations.

### Profiles

There's a special profile defined in the POM file of client and server modules:
`dev`, which is used only when developing. It configures the Jetty
plugin and speeds up development with `gwt:run-codeserver`, `gwt:run` and
`jetty:start` by not requiring a restart when a change to the
`${rootArtifactId}-shared` is made.

To activate the `dev` profile you can provide the `-Ddev` system property, or
use `-Pdev`.

### Productivity tips

When working on the server-side code exclusively, you don't need GWT's DevMode.
You can then compile the GWT app using `mvn package` or `mvn package -Dgwt.draftCompile`
and then `cd *-server && mvn jetty:start -Ddev`. The
webapp will be redeployed automatically when you change a class (either
compiled by your IDE, or by `mvn compile`) in either the
`${rootArtifactId}-server` or `${rootArtifactId}-shared` module (be careful
though when changing classes in `shared` that you do not break the GWT client
code, particularly when using GWT-RPC).

When working on the client-side code exclusively, to quickly test it in a
browser in production mode, use `mvn package -Dgwt.draftCompile`. You can use
`mvn package -Dgwt.draftCompile -pl :${rootArtifactId}-client -am` while the
Jetty server is running (launched by `cd *-server && mvn jetty:start -Ddev`),
and then simply hit `F5` in your browser.
