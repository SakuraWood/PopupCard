# PopupCard
## Setting
### Gradle
In your build.gradle(Project):
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
add this to your build.gradle(module):
```
compile 'com.github.SakuraWood:PopupCard:0.0.8'
```

## How to Use
```
  popupCard.setHeight(250).       //the height of this card
            setWidth(150).        //the width of this card
            setActivity(this).    //the current activity
            setRound(10).         //the round of this card
            setCaret(true).       //set the caret
            setRadis(120).        //the radis of the caret on the card
            setX(50).             //the offset of the caret on the card
            setY(15).             //the height of the caret on the card
            setW(10).             //the width of the caret on the card
            setDefaultAnime(true).//set the default anime of this card
            setColor(Color.BLUE). //set the color of this card
            setDown(true).        //set the caret up or down
            showContentAt(targetView, ContentView);     //add the content, and show the position beside of targetView
```
