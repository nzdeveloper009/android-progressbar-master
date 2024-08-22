android-square-progressbar
==========================
## General idea
Sometimes you don't have enough space in your layout to display a wide progressbar. So this library gives you a complete new possibility to display a progress. You can simply show a progressbar around an image. And this progressbar can be configured in a lot of different ways, like colour, outline, display of the percentage and so on.

### Examples
Here are some examples of how these progressbars could look like:

| normal / default       | rounded corners           | show percent  | indeterminate|
| ------------- | ------------- | ----- | ----- |
|        | `roundedCorners = true`          | `showProgress(true)` |  `drawOutline(true)`    |     
|        |         | `setOpacity(true)` |  `isIndeterminate = true`    |

There are some further examples available here (with code) : [Examples](https://github.com/nzdeveloper009/android-progressbar-master/wiki/Examples)
### How to use it? / How to install? / How to contribute?
Check the wiki for more information about [how to use](https://github.com/nzdeveloper009/android-progressbar-master/wiki/Usage), or [how to contribute](https://github.com/nzdeveloper009/android-progressbar-master/wiki/How-To-Contribute).

If you have questions about the code or if you need some help, you can try the [Gitter-Group](https://gitter.im/mrwonderman/android-square-progressbar).

## Usage
### Gradle
Just add the following repository to your settings.gradle.kts:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

Then in your app build.gradle:

    dependencies {
        // other repos ...
        implementation("com.github.nzdeveloper009:android-progressbar-master:1.0.0")
    }

### Code
After adding the gradle depedency from above you can go to your xml layout and add the following code for a squareprogressbar:

    <com.nokhaiz.lib.SquareProgressBar
        android:id="@+id/sprogressbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true"
        android:paddingTop="20dp" >
    </com.nokhaiz.lib.SquareProgressBar>

To set some basic settings use the following java-code:

    SquareProgressBar squareProgressBar = findViewById(R.id.sprogressbar);
    squareProgressBar.setImage(R.drawable.example);
    squareProgressBar.setProgress(50.0);

Now you can make the squareprogressbar as fancy as you like. Check the [usage page](https://github.com/nzdeveloper009/android-progressbar-master/wiki/Usage) for all the different possiblities.
