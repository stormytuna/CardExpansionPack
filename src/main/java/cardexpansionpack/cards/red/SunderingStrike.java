package cardexpansionpack.cards.red;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class SunderingStrike extends BaseCard {
    public static final String ID = makeID("SunderingStrike");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.ATTACK,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        2
    );

    private static final int DAMAGE = 10;
    private static final int DAMAGE_UPGRADE = 4;
    private static final int DAMAGE_PER_ENERGY = 10;

    public SunderingStrike() {
        super(ID, INFO);
        setDamage(DAMAGE, DAMAGE_UPGRADE);
        setMagic(DAMAGE_PER_ENERGY);
        setExhaust(true);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SunderingStrike();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage);
        addToBot(new DamageCallbackAction(m, info, AttackEffect.SLASH_DIAGONAL, damageDealt -> {
            int energy = damageDealt / magicNumber;
            if (energy > 0) {
                addToBot(new GainEnergyAction(energy));
            }
        }));
    }
}

