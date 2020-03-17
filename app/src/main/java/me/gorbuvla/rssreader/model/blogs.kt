package me.gorbuvla.rssreader.model

import me.gorbuvla.rssreader.model.domain.FeedDetail
import org.threeten.bp.ZonedDateTime

/**
 * Static data for feed content
 */

val blogsHavryluk = listOf(
    FeedDetail(
        1,
        "Konec dotovaných androidů Nexus se blíží. Náhrada je dražší",
        "Poslední dobou se začaly šířit spekulace, že série referenčních přístrojů Nexus brzy skončí a letos se dočkáme posledních zástupců. Jaký je vlastně význam této modelové řady a není při současném rozkvětu Androidu a nástupu Google Play Edition zařízení již trochu zbytečná?" +
                "Se zprávou o konci série Nexus před časem přišel známý ruský novinář Eldar Murtazin, který má často přesné informace o směřování celého mobilního světa. Spekulace byly posíleny ještě po podepsání velmi úzké spolupráce mezi společnostmi Google a Samsung, kdy si prý jihokorejský gigant vymohl právě zrušení série Nexus ve prospěch zařízení Google Play Edition. Tato zařízení jsou variantami většinou špičkových modelů bez úprav v operačním systému Android prodávané přes Play Store.",
        ZonedDateTime.now()
    ),
    FeedDetail(
        2,
        "Ničitel zažitých pravidel. Recenze LG G2",
        "Společnost LG se s modelem G2 pustila tak trochu na tenký led, porušila několik zažitých pravidel a nová naopak přidala. Tím nejpodstatnějším faktem je určitě umístění všech hardwarových tlačítek na zadní stranu zařízení. Naopak LG vybrousilo softwarovou podobu zařízení na špičkovou úroveň a přidalo spoustu užitečných funkcí, které nejsou jenom zajímavou hračkou, ale jejich užitečnost oceníte při každodenním používání. Stačí zmínit například volbu téměř libovolné kombinace předních dotykových tlačítek, režim hosta telefonu či celou řadu šikovných gest. Nutno podotknout, že Samsung je na tom v rámci proměny základní verze Androidu o nejrůznější dodatečné funkce ještě dále.",
        ZonedDateTime.now()
    )
)

val blogsCermak = listOf(
    FeedDetail(
        3,
        "Ondřej is curious",
        "Nice to hear about R8 optimizations from @chethaase and @romainguy at #kotlinconf, but I'm wondering, it doesn't seem like anything dex specific, so why can't those be done by javac or kotlinc directly?",
        ZonedDateTime.now()
    ),
    FeedDetail(
        4,
        "Ondřej is disappointed",
        "Developer's career is a path from \"wow, what an elaborate and complex system, I don't understand any of it\" to \"wow, what kind of shit is that, I don't understand any of it\".",
        ZonedDateTime.now()
    )
)

val blogsSkoumal = listOf(
    FeedDetail(
        5,
        "If a job’s worth doing, it’s worth doing well",
        "A company is a complex, living organism where everyone has something to contribute with. Big companies, on one hand, typically divide tasks in rigid patterns. Each employee has a predefined day-to-day list of duties. If they cannot find joy in their occupation, there is no other chance but to change either the position or the employer. Doesn’t sound like fulfilling division of team tasks, does it? Smaller companies, on the other hand, tend to have loosely defined team roles. Colleagues often lend a helping hand (or many hands) wherever the team leader needs them to. This other extreme makes team members lose their focus as they just wander through tasks, unable to zero in on specific problems.",
        ZonedDateTime.now()
    ),
    FeedDetail(
        6,
        "Fonts in mobile apps for developers",
        "Fonts in mobile apps are not defined in pixels. We need more abstract units to cover all displays with different densities where the text with size defined in pixels will be really big or really tiny. All mobile platforms have units preserving the same physical size across all display densities. It could imagined as defining font size in millimeters.\n" +
                "When you are developing for Android platform the proper unit is SP (scalable independent-pixel), the equivalent of pixel size on 160 DPI display (mdpi). This unit enforces the same physical font size on all devices and allow users to change font size in system settings.\n" +
                "Developers on iOS platform should define font size in PT (point). This is again the equivalent of pixel size for 160 DPI display.",
        ZonedDateTime.now()
    )
)

val blogsBildik = listOf(
    FeedDetail(
        7,
        "My Droidcon Torino \uD83C\uDDEE\uD83C\uDDF9",
        "I visited Droidcon in Torino last week and it was a wonderful experience. I was in Torino two years ago, last year I cheated on it and I visited AppBuilders in Lugano, but when email with early bird tickets reminder arrived this February, I did not hesitate for a minute and bought it immediately. One could think that going to the same conference repeatedly is not a smart idea when there are many of other events and visiting such event is a great opportunity to explore new cities and cultures, but it seems that I am not that kind of a person.\n" +
                "When I’ve visited Torino 2 years ago it was my first foreign experience with programming conference (as I live in Prague) so everything was pretty new to me. I came with my colleague Georgiy Shur and we were in a process of learning how to build better and more solid application architecture and we were struck with so many great content. We’ve adopted pretty much every tip and pattern that we’ve seen from more readable build.gradle files, faster builds times, better MVP architecture (yep, MVP \uD83D\uDC74) to repository pattern implemented with Store library from NY Times. Kotlin was also pretty new back then and still not officially supported by Google so it was also great to see other developers use it. We use all this knowledge up to day so I was a little disappointed last year when AppBuilders did not deliver the same outcome (my guess is that the AppBuilders is more oriented on beginners but who knows). But when I saw agenda of Torino I knew that it will happen again and I will come home full of ideas and inspiration.",
        ZonedDateTime.now()
    ),
    FeedDetail(
        8,
        "Anko tips&tricks #1",
        "Some time ago I decided to try Kotlin in production and a few weeks ago I started developing an application that is the one, the one with Kotlin under the hood. When I discovered Anko library I was amazed by its simplicity and advantages over standard xml approach.\n" +
                "A lot of great articles were written about introduction to Anko (like here or here) and this series of mini posts shouldn’t be one of them. My idea is that this will be just a bunch of neat tips and tricks that I (or my colleagues) uses, discovers and we don’t want to hide them under pillow.\n" +
                "Disclaimer: I am very new to Anko and Kotlin in general and it’s possible that sometimes I am reinventing the wheel or that some thing could be done in better way. Please, feel free to point it up in discussion. This feedback is very welcome (or any other feedback as well :))\n" +
                "Tip #1 — View Extension properties\n" +
                "Every designer loves to differentiate texts in application with typefaces — bold, medium, light, regular, italic and you can combine most of them. The Android framework provides few ways how to easily apply some typefaces to TextView like textStyle or fontFamily xml attribute. With textStyle you can make text bold, italic or bolditalic. This is just a subset of Roboto typefaces and if you want to make text for example medium typeface, you have to use other ways. fontFamily is other attribute that you can use. You define name of font family you want to use from predefined set of font families. The problem is that medium typeface mentioned above is added in Android Lollipop and be honest, do you have minSdk on 21? I don’t think so. The solution is to copy the font files (Roboto-Regular.ttf, Roboto-Bold.ttf, Roboto-Medium.ttf, …) into your application assets and set custom typeface to TextView. But you don’t want to set typeface to every TextView in your application in Java code.",
        ZonedDateTime.now()
    )
)