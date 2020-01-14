import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './CartFlusher.soy';

let notificationDidShow = false;

function showNotification(message, type) {
	!notificationDidShow && AUI().use(
		'liferay-notification',
		() => {
			new Liferay.Notification(
				{
					closeable: true,
					delay: {
						hide: 5000,
						show: 0
					},
					duration: 500,
					message: message,
					render: true,
					title: '',
					type: type
				}
			);
		}
	);

	notificationDidShow = true;

	setTimeout(() => {
		notificationDidShow = false;
	}, 500);
}

class CartFlusher extends Component {
	_handleAskConfirmation(e) {
		e.preventDefault();

		this.isAsking = true;
	}

	_handleCancel() {
		this.isAsking = false;
	}

	_handleConfirm() {
		fetch(this.apiEndpoint, {method: 'DELETE'})
			.then(response => response.json())
			.then(({success, products, summary}) => {
				this.isAsking = false;

				if (success && (!products.length || !products)) {
					this.emit('deleteAllItems', {products: null, summary});
				} else {
					throw new Error(Liferay.Language.get('unable-to-empty-the-cart'));
				}
			})
			.catch(e => {
				this.isAsking = false;

				showNotification(e, 'danger');
			});
	}
}

Soy.register(CartFlusher, template);

CartFlusher.STATE = {
	apiEndpoint: Config.string(),
	isAsking: Config.bool().value(false)
};

export {CartFlusher};
export default CartFlusher;