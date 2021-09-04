/**
 * 사용자 정의 지시자 만들기!!
 
 * 밑에 보면 v-focus 이린거 원래 없음 만드는거임!
 * v-on 같은거를 직접 만드는거임!!!!!
 *
 * 지시자 파일명은 ${directiveName}.directive.js 형식으로 저장한다.
 * 이렇게 하면 파일 이름만으로도 접미사를 보고 지시자파일임을 알 수 있다.
 *
 * 이걸 template 쪽에서 import 하여 사용한다.
 */
Vue.directive( 'focus', {
	// 바인딩된 요소가 DOM 으로 삽입될 때.
	inserted : function( el ) {
		el.focus();
	}
});